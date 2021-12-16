package net.datenwerke.rs.dropbox.server.dropbox;

import static net.datenwerke.rs.utils.exception.shared.LambdaExceptionUtil.rethrowFunction;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import javax.inject.Singleton;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ExpectedException;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.client.datasinkmanager.DatasinkTestFailedException;
import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkDefinitionDto;
import net.datenwerke.rs.core.client.reportexporter.dto.ReportExecutionConfigDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.core.server.reportexport.hooks.ReportExportViaSessionHook;
import net.datenwerke.rs.core.service.datasinkmanager.DatasinkService;
import net.datenwerke.rs.core.service.datasinkmanager.configs.DatasinkFilenameFolderConfig;
import net.datenwerke.rs.core.service.datasinkmanager.entities.DatasinkDefinition;
import net.datenwerke.rs.core.service.reportmanager.ReportDtoService;
import net.datenwerke.rs.core.service.reportmanager.ReportExecutorService;
import net.datenwerke.rs.core.service.reportmanager.ReportService;
import net.datenwerke.rs.core.service.reportmanager.engine.CompiledReport;
import net.datenwerke.rs.core.service.reportmanager.engine.config.RECReportExecutorToken;
import net.datenwerke.rs.core.service.reportmanager.engine.config.ReportExecutionConfig;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.dropbox.client.dropbox.dto.DropboxDatasinkDto;
import net.datenwerke.rs.dropbox.client.dropbox.rpc.DropboxRpcService;
import net.datenwerke.rs.dropbox.service.dropbox.DropboxService;
import net.datenwerke.rs.dropbox.service.dropbox.definitions.DropboxDatasink;
import net.datenwerke.rs.fileserver.client.fileserver.dto.AbstractFileServerNodeDto;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;
import net.datenwerke.rs.utils.exception.ExceptionServices;
import net.datenwerke.rs.utils.zip.ZipUtilsService;
import net.datenwerke.security.server.SecuredRemoteServiceServlet;
import net.datenwerke.security.service.security.SecurityService;
import net.datenwerke.security.service.security.rights.Execute;
import net.datenwerke.security.service.security.rights.Read;

@Singleton
public class DropboxRpcServiceImpl extends SecuredRemoteServiceServlet implements DropboxRpcService {

   /**
    * 
    */
   private static final long serialVersionUID = -2851245535282732136L;

   private final ReportService reportService;
   private final DtoService dtoService;
   private final ReportExecutorService reportExecutorService;
   private final ReportDtoService reportDtoService;
   private final HookHandlerService hookHandlerService;
   private final DropboxService dropboxService;
   private final SecurityService securityService;
   private final ExceptionServices exceptionServices;
   private final ZipUtilsService zipUtilsService;
   private final Provider<DatasinkService> datasinkServiceProvider;

   @Inject
   public DropboxRpcServiceImpl(
         ReportService reportService, 
         ReportDtoService reportDtoService, 
         DtoService dtoService,
         ReportExecutorService reportExecutorService, 
         SecurityService securityService,
         HookHandlerService hookHandlerService, 
         DropboxService dropboxService, 
         ExceptionServices exceptionServices, 
         ZipUtilsService zipUtilsService,
         Provider<DatasinkService> datasinkServiceProvider
         ) {

      this.reportService = reportService;
      this.reportDtoService = reportDtoService;
      this.dtoService = dtoService;
      this.reportExecutorService = reportExecutorService;
      this.securityService = securityService;
      this.hookHandlerService = hookHandlerService;
      this.dropboxService = dropboxService;
      this.exceptionServices = exceptionServices;
      this.zipUtilsService = zipUtilsService;
      this.datasinkServiceProvider = datasinkServiceProvider;
   }

   @Override
   public void exportIntoDropbox(ReportDto reportDto, String executorToken, DropboxDatasinkDto dropboxDatasinkDto,
         String format, List<ReportExecutionConfigDto> configs, String name, String folder, boolean compressed)
         throws ServerCallFailedException {
      final ReportExecutionConfig[] configArray = getConfigArray(executorToken, configs);

      DropboxDatasink dropboxDatasink = (DropboxDatasink) dtoService.loadPoso(dropboxDatasinkDto);

      /* get a clean and unmanaged report from the database */
      Report referenceReport = reportDtoService.getReferenceReport(reportDto);
      Report orgReport = (Report) reportService.getUnmanagedReportById(reportDto.getId());

      /* check rights */
      securityService.assertRights(referenceReport, Execute.class);
      securityService.assertRights(dropboxDatasink, Read.class, Execute.class);

      /* create variant */
      Report adjustedReport = (Report) dtoService.createUnmanagedPoso(reportDto);
      final Report toExecute = orgReport.createTemporaryVariant(adjustedReport);

      hookHandlerService.getHookers(ReportExportViaSessionHook.class)
            .forEach(hooker -> hooker.adjustReport(toExecute, configArray));

      CompiledReport cReport;
      try {
         cReport = reportExecutorService.execute(toExecute, format, configArray);

         if (compressed) {
            String filename = name + ".zip";
            try (ByteArrayOutputStream os = new ByteArrayOutputStream()) {
               Object reportObj = cReport.getReport();
               zipUtilsService.createZip(
                     zipUtilsService.cleanFilename(toExecute.getName() + "." + cReport.getFileExtension()),
                     reportObj, os);
               datasinkServiceProvider.get().exportIntoDatasink(os.toByteArray(), dropboxDatasink, dropboxService,
                     new DatasinkFilenameFolderConfig() {

                  @Override
                  public String getFilename() {
                     return filename;
                  }

                  @Override
                  public String getFolder() {
                     return folder;
                  }

               });
            }
         } else {
            String filename = name + "." + cReport.getFileExtension();
            datasinkServiceProvider.get().exportIntoDatasink(cReport.getReport(), dropboxDatasink, dropboxService,
                  new DatasinkFilenameFolderConfig() {

               @Override
               public String getFilename() {
                  return filename;
               }

               @Override
               public String getFolder() {
                  return folder;
               }

            });
         }
      } catch (Exception e) {
         throw new ServerCallFailedException("Could not send to Dropbox: " + e.getMessage(), e);
      }

   }

   private ReportExecutionConfig[] getConfigArray(final String executorToken,
         final List<ReportExecutionConfigDto> configs) throws ExpectedException {
      return Stream.concat(
            configs.stream().map(rethrowFunction(config -> (ReportExecutionConfig) dtoService.createPoso(config))),
            Stream.of(new RECReportExecutorToken(executorToken))).toArray(ReportExecutionConfig[]::new);
   }

   @Override
   public Map<StorageType, Boolean> getStorageEnabledConfigs() throws ServerCallFailedException {
      return datasinkServiceProvider.get().getEnabledConfigs(dropboxService);
   }

   @Override
   public boolean testDropboxDatasink(DropboxDatasinkDto dropboxDatasinkDto) throws ServerCallFailedException {
      DropboxDatasink dropboxDatasink = (DropboxDatasink) dtoService.loadPoso(dropboxDatasinkDto);

      /* check rights */
      securityService.assertRights(dropboxDatasink, Read.class, Execute.class);

      try {
         datasinkServiceProvider.get().testDatasink(dropboxDatasink, dropboxService, new DatasinkFilenameFolderConfig() {

            @Override
            public String getFilename() {
               return "reportserver-dropbox-test.txt";
            }

            @Override
            public String getFolder() {
               return dropboxDatasink.getFolder();
            }

         });
      } catch (Exception e) {
         DatasinkTestFailedException ex = new DatasinkTestFailedException(e.getMessage(), e);
         ex.setStackTraceAsString(exceptionServices.exceptionToString(e));
         throw ex;
      }

      return true;
   }
   
   @Override
   public DatasinkDefinitionDto getDefaultDatasink() throws ServerCallFailedException {

      Optional<? extends DatasinkDefinition> defaultDatasink = datasinkServiceProvider.get()
            .getDefaultDatasink(dropboxService);
      if (!defaultDatasink.isPresent())
         return null;

      /* check rights */
      securityService.assertRights(defaultDatasink.get(), Read.class);

      return (DatasinkDefinitionDto) dtoService.createDto(defaultDatasink.get());
   }
   
   @Override
   public void exportFileIntoDatasink(AbstractFileServerNodeDto abstractNodeDto, DatasinkDefinitionDto datasinkDto, String filename,
         String folder,boolean compressed) throws ServerCallFailedException {
      /* check rights */
      securityService.assertRights(abstractNodeDto, Read.class);
      securityService.assertRights(datasinkDto, Read.class, Execute.class);
      datasinkServiceProvider.get().exportFileIntoDatasink(abstractNodeDto, datasinkDto, dropboxService, filename,
            folder, compressed);
   }

}
