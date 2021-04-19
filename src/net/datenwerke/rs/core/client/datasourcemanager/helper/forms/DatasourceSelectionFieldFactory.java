package net.datenwerke.rs.core.client.datasourcemanager.helper.forms;

import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.rs.core.client.datasourcemanager.DatasourceDao;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceDefinitionDto;

import com.sencha.gxt.widget.core.client.container.Container;

public interface DatasourceSelectionFieldFactory {

	public DatasourceSelectionField create(
		boolean displayConfigFields,
		Container container, 
		UITree datasourceTree,
		DatasourceDao generalPropertiesDao, 
		Class<? extends DatasourceDefinitionDto>[] types);
}
