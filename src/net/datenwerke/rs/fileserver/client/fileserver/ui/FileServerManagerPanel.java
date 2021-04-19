package net.datenwerke.rs.fileserver.client.fileserver.ui;

import net.datenwerke.gf.client.managerhelper.ui.AbstractTreeManagerPanel;
import net.datenwerke.rs.fileserver.client.fileserver.locale.FileServerMessages;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class FileServerManagerPanel extends AbstractTreeManagerPanel {

	@Inject
	public FileServerManagerPanel(
		FileServerManagerMainPanel mainPanel,
		FileServerManagerTreePanel treePanel
		){
		
		super(mainPanel, treePanel);
	}

	@Override
	protected String getHeadingText() {
		return FileServerMessages.INSTANCE.mainPanelHeading();
	}
	
}
