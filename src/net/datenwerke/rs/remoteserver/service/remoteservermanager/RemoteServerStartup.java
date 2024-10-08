package net.datenwerke.rs.remoteserver.service.remoteservermanager;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.eximport.hooks.ExporterProviderHook;
import net.datenwerke.eximport.hooks.ImporterProviderHook;
import net.datenwerke.gf.service.history.hooks.HistoryUrlBuilderHook;
import net.datenwerke.gf.service.lifecycle.hooks.ConfigDoneHook;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.adminutils.service.systemconsole.generalinfo.hooks.GeneralInfoCategoryProviderHook;
import net.datenwerke.rs.base.ext.service.hooks.ExportConfigHook;
import net.datenwerke.rs.base.ext.service.hooks.RemoteEntityImporterHook;
import net.datenwerke.rs.eximport.service.eximport.im.http.hooks.HttpImportConfigurationProviderHook;
import net.datenwerke.rs.remoteserver.service.remoteservermanager.entities.RemoteServerFolder;
import net.datenwerke.rs.remoteserver.service.remoteservermanager.eximport.HttpRemoteServerManagerImportConfigurationHooker;
import net.datenwerke.rs.remoteserver.service.remoteservermanager.eximport.RemoteServerManagerExporter;
import net.datenwerke.rs.remoteserver.service.remoteservermanager.eximport.RemoteServerManagerImporter;
import net.datenwerke.rs.remoteserver.service.remoteservermanager.eximport.hookers.RemoteRemoteServerImporterHooker;
import net.datenwerke.rs.remoteserver.service.remoteservermanager.eximport.hookers.RemoteServerExportConfigHooker;
import net.datenwerke.rs.remoteserver.service.remoteservermanager.genrights.RemoteServerManagerAdminViewSecurityTarget;
import net.datenwerke.rs.remoteserver.service.remoteservermanager.history.RemoteServerManagerHistoryUrlBuilderHooker;
import net.datenwerke.rs.remoteserver.service.remoteservermanager.hookers.RemoteServerCategoryProviderHooker;
import net.datenwerke.rs.remoteserver.service.remoteservermanager.hookers.UsageStatisticsRemoteServersFoldersProviderHooker;
import net.datenwerke.rs.remoteserver.service.remoteservermanager.hookers.UsageStatisticsTotalRemoteServersProviderHooker;
import net.datenwerke.rs.remoteserver.service.remoteservermanager.hooks.RemoteServerEntryProviderHook;
import net.datenwerke.rs.terminal.service.terminal.hookers.RemoteServerOpenTerminalHooker;
import net.datenwerke.rs.terminal.service.terminal.hooks.OpenTerminalHandlerHook;
import net.datenwerke.rs.utils.eventbus.EventBus;
import net.datenwerke.security.service.security.SecurityService;

public class RemoteServerStartup {

   @Inject
   public RemoteServerStartup(
         final HookHandlerService hookHandler, 
         final EventBus eventBus,
         final Provider<SecurityService> securityServiceProvider,
         final Provider<RemoteServerManagerHistoryUrlBuilderHooker> remoteServerManagerUrlBuilder,
         final Provider<UsageStatisticsTotalRemoteServersProviderHooker> usageStatsTotalRemoteServerProvider,
         final Provider<RemoteServerCategoryProviderHooker> usageStatistics,
         final Provider<RemoteServerExportConfigHooker> remoteServerExportConfigHookerProvider,
         final Provider<RemoteRemoteServerImporterHooker> remoteRemoteServerImporterHooker,
         final Provider<UsageStatisticsRemoteServersFoldersProviderHooker> usageStatsFolderProvider,
         final Provider<RemoteServerManagerExporter> remoteServerManagerExporter,
         final Provider<RemoteServerManagerImporter> remoteServerImporterProvider,
         final Provider<HttpRemoteServerManagerImportConfigurationHooker> remoteServerHttpImportConfigHookerProvider,
         final Provider<RemoteServerOpenTerminalHooker> remoteServerOpenTerminalHooker
         ) {

      /* history */
      hookHandler.attachHooker(HistoryUrlBuilderHook.class, remoteServerManagerUrlBuilder);
      
      hookHandler.attachHooker(RemoteServerEntryProviderHook.class, usageStatsTotalRemoteServerProvider,
            HookHandlerService.PRIORITY_LOW);
      hookHandler.attachHooker(RemoteServerEntryProviderHook.class, usageStatsFolderProvider,
            HookHandlerService.PRIORITY_LOW + 3);
      
      hookHandler.attachHooker(GeneralInfoCategoryProviderHook.class, usageStatistics,
            HookHandlerService.PRIORITY_LOW + 70);
      
      hookHandler.attachHooker(ExportConfigHook.class, remoteServerExportConfigHookerProvider);
      
      hookHandler.attachHooker(RemoteEntityImporterHook.class, remoteRemoteServerImporterHooker);
      
      hookHandler.attachHooker(ExporterProviderHook.class, new ExporterProviderHook(remoteServerManagerExporter));
      hookHandler.attachHooker(ImporterProviderHook.class, new ImporterProviderHook(remoteServerImporterProvider));
      hookHandler.attachHooker(HttpImportConfigurationProviderHook.class, remoteServerHttpImportConfigHookerProvider);
      hookHandler.attachHooker(OpenTerminalHandlerHook.class, remoteServerOpenTerminalHooker);
      
      /* register security targets */
      hookHandler.attachHooker(ConfigDoneHook.class, () -> {
         /* secure folder */
         securityServiceProvider.get().registerSecurityTarget(RemoteServerFolder.class);
         
         securityServiceProvider.get().registerSecurityTarget(RemoteServerManagerAdminViewSecurityTarget.class);
      });
   }
}
