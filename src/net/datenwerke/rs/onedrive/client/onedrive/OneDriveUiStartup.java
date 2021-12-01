package net.datenwerke.rs.onedrive.client.onedrive;

import java.util.Map;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.client.login.LoginService;
import net.datenwerke.gf.client.managerhelper.hooks.MainPanelViewToolbarConfiguratorHook;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.waitonevent.WaitOnEventUIService;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.client.datasinkmanager.hooks.DatasinkDefinitionConfigProviderHook;
import net.datenwerke.rs.core.client.reportexporter.hooks.ExportExternalEntryProviderHook;
import net.datenwerke.rs.fileserver.client.fileserver.provider.treehooks.FileExportExternalEntryProviderHook;
import net.datenwerke.rs.onedrive.client.onedrive.hookers.ExportToOneDriveHooker;
import net.datenwerke.rs.onedrive.client.onedrive.hookers.FileExportToOneDriveHooker;
import net.datenwerke.rs.onedrive.client.onedrive.hookers.OneDriveDatasinkConfigProviderHooker;
import net.datenwerke.rs.onedrive.client.onedrive.hookers.OneDriveDatasinkOAuthToolbarConfigurator;
import net.datenwerke.rs.onedrive.client.onedrive.hookers.OneDriveDatasinkTesterToolbarConfigurator;
import net.datenwerke.rs.onedrive.client.onedrive.hookers.OneDriveExportSnippetProvider;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;
import net.datenwerke.rs.scheduler.client.scheduler.hooks.ScheduleExportSnippetProviderHook;

public class OneDriveUiStartup {
   @Inject
   public OneDriveUiStartup(
         final Provider<ExportToOneDriveHooker> exportToOneDriveHooker,
         final Provider<FileExportToOneDriveHooker> fileExportToDatasinkHooker,
         final HookHandlerService hookHandler, 
         final WaitOnEventUIService waitOnEventService, 
         final OneDriveDao dao,
         final Provider<OneDriveDatasinkConfigProviderHooker> oneDriveTreeConfiguratorProvider,
         final OneDriveDatasinkTesterToolbarConfigurator oneDriveTestToolbarConfigurator,
         final OneDriveDatasinkOAuthToolbarConfigurator oneDriveOauthToolbarConfigurator,
         final Provider<OneDriveExportSnippetProvider> oneDriveExportSnippetProvider,
         final OneDriveUiService oneDriveUiService
         ) {
      /* config tree */
      hookHandler.attachHooker(DatasinkDefinitionConfigProviderHook.class, oneDriveTreeConfiguratorProvider.get(),
            HookHandlerService.PRIORITY_LOW + 45);

      /* Send-to hookers */
      hookHandler.attachHooker(ExportExternalEntryProviderHook.class, exportToOneDriveHooker,
            HookHandlerService.PRIORITY_LOW + 45);
      hookHandler.attachHooker(FileExportExternalEntryProviderHook.class, fileExportToDatasinkHooker,
            HookHandlerService.PRIORITY_LOW + 45);

      /* test datasinks */
      hookHandler.attachHooker(MainPanelViewToolbarConfiguratorHook.class, oneDriveTestToolbarConfigurator,
            HookHandlerService.PRIORITY_HIGH);

      /* Oauth */
      hookHandler.attachHooker(MainPanelViewToolbarConfiguratorHook.class, oneDriveOauthToolbarConfigurator);

      waitOnEventService.callbackOnEvent(LoginService.REPORTSERVER_EVENT_AFTER_ANY_LOGIN, ticket -> {
         waitOnEventService.signalProcessingDone(ticket);

         dao.getStorageEnabledConfigs(new RsAsyncCallback<Map<StorageType, Boolean>>() {
            @Override
            public void onSuccess(final Map<StorageType, Boolean> result) {
               ((OneDriveUiServiceImpl)oneDriveUiService).setEnabledConfigs(result);
               if (result.get(StorageType.ONEDRIVE) && result.get(StorageType.ONEDRIVE_SCHEDULING))
                  hookHandler.attachHooker(ScheduleExportSnippetProviderHook.class, oneDriveExportSnippetProvider,
                        HookHandlerService.PRIORITY_LOW + 45);
               else
                  hookHandler.detachHooker(ScheduleExportSnippetProviderHook.class, oneDriveExportSnippetProvider);
            }

            @Override
            public void onFailure(Throwable caught) {
               super.onFailure(caught);
            }
         });

      });
   }
}