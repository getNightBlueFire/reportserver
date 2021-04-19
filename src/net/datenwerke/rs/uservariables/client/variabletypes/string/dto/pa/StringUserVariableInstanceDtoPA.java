package net.datenwerke.rs.uservariables.client.variabletypes.string.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.String;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.uservariables.client.uservariables.dto.pa.UserVariableInstanceDtoPA;
import net.datenwerke.rs.uservariables.client.variabletypes.string.dto.StringUserVariableInstanceDto;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.uservariables.service.variabletypes.string.StringUserVariableInstance.class)
public interface StringUserVariableInstanceDtoPA extends UserVariableInstanceDtoPA {


	public static final StringUserVariableInstanceDtoPA INSTANCE = GWT.create(StringUserVariableInstanceDtoPA.class);


	/* Properties */
	public ValueProvider<StringUserVariableInstanceDto,String> value();


}
