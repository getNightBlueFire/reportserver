package net.datenwerke.gf.client.treedb.helper.menu;

import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.gxtdto.client.servercommunication.callback.NotamCallback;
import net.datenwerke.security.client.security.dto.WriteDto;
import net.datenwerke.security.client.treedb.dto.decorator.SecuredAbstractNodeDtoDec;
import net.datenwerke.treedb.client.treedb.TreeDbManagerDao;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;
import net.datenwerke.treedb.client.treedb.locale.TreedbMessages;

public class DuplicateMenuItem extends TreeMenuItem {
	
	public DuplicateMenuItem(final TreeDbManagerDao treeManager){
		super();
		
		setText(TreedbMessages.INSTANCE.duplicateText());
		addMenuSelectionListener(new TreeMenuSelectionEvent() {
			public void menuItemSelected(final UITree tree, final AbstractNodeDto node) {
				treeManager.duplicateNode(node, new NotamCallback<AbstractNodeDto>(TreedbMessages.INSTANCE.duplicated()) {});
			}
		});
	}

	@Override
	public void toBeDisplayed(AbstractNodeDto selectedItem) {
		disable();
	
		/* try to get parent */
		AbstractNodeDto parent = tree.getParentNode(selectedItem);
		if(null != parent && 
				(! (parent instanceof SecuredAbstractNodeDtoDec) || 
				 ! ((SecuredAbstractNodeDtoDec)parent).isAccessRightsLoaded() || 
				 ( 	
				    ((SecuredAbstractNodeDtoDec)parent).hasAccessRight(WriteDto.class) &&
				    ((SecuredAbstractNodeDtoDec)parent).hasInheritedAccessRight(WriteDto.class)
				 )	
				)
		){
			enable();
		}
	}
}
