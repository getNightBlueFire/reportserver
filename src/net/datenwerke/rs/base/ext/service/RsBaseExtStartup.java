package net.datenwerke.rs.base.ext.service;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.eximport.hooks.ExporterProviderHook;
import net.datenwerke.eximport.hooks.ImporterProviderHook;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.base.ext.service.dashboardmanager.eximport.DashboardManagerExporter;
import net.datenwerke.rs.base.ext.service.dashboardmanager.eximport.DashboardManagerImporter;
import net.datenwerke.rs.base.ext.service.dashboardmanager.eximport.HttpDashboardManagerImportConfigurationHooker;
import net.datenwerke.rs.base.ext.service.dashboardmanager.eximport.hookers.DashboardManagerExportConfigHooker;
import net.datenwerke.rs.base.ext.service.dashboardmanager.eximport.hookers.RemoteDashboardImporterHooker;
import net.datenwerke.rs.base.ext.service.hooks.ExportConfigHook;
import net.datenwerke.rs.base.ext.service.hooks.RemoteEntityImporterHook;
import net.datenwerke.rs.base.ext.service.reportmanager.eximport.HttpReportManagerImportConfigurationHooker;
import net.datenwerke.rs.base.ext.service.reportmanager.eximport.ParameterInstanceExporter;
import net.datenwerke.rs.base.ext.service.reportmanager.eximport.ReportManagerExporter;
import net.datenwerke.rs.base.ext.service.reportmanager.eximport.ReportManagerImporter;
import net.datenwerke.rs.base.ext.service.reportmanager.eximport.hookers.ExportAllReportsHooker;
import net.datenwerke.rs.base.ext.service.reportmanager.eximport.hookers.ImportAllReportsHooker;
import net.datenwerke.rs.base.ext.service.reportmanager.eximport.hookers.RemoteReportImporterHooker;
import net.datenwerke.rs.base.ext.service.reportmanager.eximport.hookers.ReportExportConfigHooker;
import net.datenwerke.rs.base.ext.service.reportmanager.hooks.ReportModSubCommandHook;
import net.datenwerke.rs.base.ext.service.reportmanager.terminal.commands.ListPropertyCommand;
import net.datenwerke.rs.base.ext.service.reportmanager.terminal.commands.RemovePropertyCommand;
import net.datenwerke.rs.base.ext.service.reportmanager.terminal.commands.ReportModCommand;
import net.datenwerke.rs.base.ext.service.reportmanager.terminal.commands.SetPropertyCommand;
import net.datenwerke.rs.base.ext.service.reportmanager.terminal.commands.SetUuidCommand;
import net.datenwerke.rs.base.ext.service.security.eximport.GenericRightsExporter;
import net.datenwerke.rs.base.ext.service.security.eximport.GenericRightsImporter;
import net.datenwerke.rs.base.ext.service.security.eximport.hookers.ExportAllGenericRightsHooker;
import net.datenwerke.rs.base.ext.service.security.eximport.hookers.ImportAllGenericRightsHooker;
import net.datenwerke.rs.base.ext.service.terminal.commands.RpullCommand;
import net.datenwerke.rs.base.ext.service.terminal.commands.RpullCopySubcommand;
import net.datenwerke.rs.base.ext.service.terminal.commands.RpullSubCommandHook;
import net.datenwerke.rs.eximport.service.eximport.hooks.ExportAllHook;
import net.datenwerke.rs.eximport.service.eximport.hooks.ImportAllHook;
import net.datenwerke.rs.eximport.service.eximport.im.http.hooks.HttpImportConfigurationProviderHook;
import net.datenwerke.rs.terminal.service.terminal.hooks.TerminalCommandHook;

public class RsBaseExtStartup {

   @Inject
   public RsBaseExtStartup(HookHandlerService hookHandler,

         Provider<DashboardManagerExporter> dashboardExporterProvider,
         Provider<DashboardManagerImporter> dashboardImporterProvider,
         Provider<HttpDashboardManagerImportConfigurationHooker> dashboardHttpImportConfigHookerProvider,

         Provider<ReportManagerExporter> exporterProvider, 
         Provider<ReportManagerImporter> importerProvider,
         Provider<HttpReportManagerImportConfigurationHooker> httpImportConfigHookerProvider,
         Provider<ParameterInstanceExporter> parameterInstanceExporter,

         Provider<GenericRightsExporter> genericRightsExporterProvider,
         Provider<GenericRightsImporter> genericRIghtsImporterProvider,

         Provider<ExportAllReportsHooker> exportAllReports, 
         Provider<ImportAllReportsHooker> importAllReports,

         Provider<ExportAllGenericRightsHooker> exportAllGenericRights,
         Provider<ImportAllGenericRightsHooker> importAllGenericRights,

         Provider<ReportModCommand> reportModCommand, 
         Provider<SetUuidCommand> setUUIDCommand,
         Provider<SetPropertyCommand> setPropertyCommand, 
         Provider<RemovePropertyCommand> removePropertyCommand,
         Provider<ListPropertyCommand> listPropertyCommand,
         
         Provider<ReportExportConfigHooker> reportExportConfigHooker,
         Provider<DashboardManagerExportConfigHooker> dashboardManagerExportConfigHooker,
         
         Provider<RemoteReportImporterHooker> remoteReportImporterHooker,
         Provider<RemoteDashboardImporterHooker> remoteDashboardImporterHooker,
         
         Provider<RpullCommand> rpullCommand,
         Provider<RpullCopySubcommand> rpullCopySubcommand
         

   ) {

      hookHandler.attachHooker(ExporterProviderHook.class, new ExporterProviderHook(dashboardExporterProvider));
      hookHandler.attachHooker(ImporterProviderHook.class, new ImporterProviderHook(dashboardImporterProvider));
      hookHandler.attachHooker(HttpImportConfigurationProviderHook.class, dashboardHttpImportConfigHookerProvider);

      hookHandler.attachHooker(ExporterProviderHook.class, new ExporterProviderHook(exporterProvider));
      hookHandler.attachHooker(ImporterProviderHook.class, new ImporterProviderHook(importerProvider));
      hookHandler.attachHooker(HttpImportConfigurationProviderHook.class, httpImportConfigHookerProvider);
      hookHandler.attachHooker(ExporterProviderHook.class, new ExporterProviderHook(parameterInstanceExporter));

      hookHandler.attachHooker(ExporterProviderHook.class, new ExporterProviderHook(genericRightsExporterProvider));
      hookHandler.attachHooker(ImporterProviderHook.class, new ImporterProviderHook(genericRIghtsImporterProvider));

      hookHandler.attachHooker(ExportAllHook.class, exportAllReports);
      hookHandler.attachHooker(ImportAllHook.class, importAllReports);

      hookHandler.attachHooker(ExportAllHook.class, exportAllGenericRights);
      hookHandler.attachHooker(ImportAllHook.class, importAllGenericRights);

      hookHandler.attachHooker(TerminalCommandHook.class, reportModCommand);

      hookHandler.attachHooker(ReportModSubCommandHook.class, setUUIDCommand);
      hookHandler.attachHooker(ReportModSubCommandHook.class, setPropertyCommand);
      hookHandler.attachHooker(ReportModSubCommandHook.class, removePropertyCommand);
      hookHandler.attachHooker(ReportModSubCommandHook.class, listPropertyCommand);
      
      hookHandler.attachHooker(ExportConfigHook.class, reportExportConfigHooker);
      hookHandler.attachHooker(ExportConfigHook.class, dashboardManagerExportConfigHooker);
      
      hookHandler.attachHooker(RemoteEntityImporterHook.class, remoteReportImporterHooker);
      hookHandler.attachHooker(RemoteEntityImporterHook.class, remoteDashboardImporterHooker);
      
      hookHandler.attachHooker(TerminalCommandHook.class, rpullCommand);
      hookHandler.attachHooker(RpullSubCommandHook.class, rpullCopySubcommand);
   }
}
