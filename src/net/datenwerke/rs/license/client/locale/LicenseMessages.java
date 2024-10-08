package net.datenwerke.rs.license.client.locale;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Messages;

public interface LicenseMessages extends Messages {

   final LicenseMessages INSTANCE = GWT.create(LicenseMessages.class);

   String viewNavigationTitle();

   String dialogTitle();

   String installationDateLabel();

   String serverIdLabel();

   String informationPanelHeader();

   String currentLicenseLabel();

   String licenseExpirationDate();

   String noExpirationDateMsg();

   String purchaseEnterpriseLabel();

   String purchaseEnterpriseText();

   String updateLicenseInfoBtnLabel();

   String updateLicenseInfoFieldLabel();

   String licenseeLabel();

   String licenseExpiredLabel();

   String upgradesAvailableUntilLabel();

   String versionLabel();

   String permissionModuleDescription();
   
   String license1();
   
   String license2();
   
   String license3();
   
   String license4();
   
   String license5();
   
   String license6();
   
   String license7();
   
   String license8();
   
   String license9();

}
