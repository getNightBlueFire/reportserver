package net.datenwerke.security.ext.client.usermanager.utils;

import net.datenwerke.gf.client.treedb.icon.IconMapping;
import net.datenwerke.gxtdto.client.resources.BaseResources;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.security.client.usermanager.dto.SexDto;
import net.datenwerke.security.client.usermanager.dto.UserDto;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

import com.google.gwt.resources.client.ImageResource;

/**
 * 
 * <p>Needs static injection</p>
 *
 */
public class UserIconMapping extends IconMapping {

	
	
	public UserIconMapping() {
		super(UserDto.class);
	}

	@Override
	public ImageResource getIcon(AbstractNodeDto node) {
		if(! (node instanceof UserDto))
			throw new IllegalArgumentException("Expected UserDTO"); //$NON-NLS-1$
		UserDto user = (UserDto) node;
		if(! user.isActive())
			return BaseIcon.USER_BLOCKED.toImageResource();
		
		return BaseIcon.USER.toImageResource();
	}
}
