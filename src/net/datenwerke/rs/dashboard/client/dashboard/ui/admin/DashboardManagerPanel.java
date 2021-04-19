package net.datenwerke.rs.dashboard.client.dashboard.ui.admin;

import net.datenwerke.gf.client.managerhelper.ui.AbstractTreeManagerPanel;
import net.datenwerke.rs.dashboard.client.dashboard.locale.DashboardMessages;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class DashboardManagerPanel extends AbstractTreeManagerPanel {

	@Inject
	public DashboardManagerPanel(
		DashboardManagerMainPanel mainPanel,
		DashboardManagerTreePanel treePanel
		){
		
		super(mainPanel, treePanel);
	}

	@Override
	protected String getHeadingText() {
		return DashboardMessages.INSTANCE.mainPanelHeading();
	}
	
}
