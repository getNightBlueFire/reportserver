package net.datenwerke.rs.terminal.client.terminal.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.String;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.terminal.client.terminal.dto.CommandResultLineDto;
import net.datenwerke.rs.terminal.client.terminal.dto.decorator.CommandResultLineDtoDec;
import net.datenwerke.rs.terminal.client.terminal.dto.pa.CommandResultEntryDtoPA;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.terminal.service.terminal.obj.CommandResultLine.class)
public interface CommandResultLineDtoPA extends CommandResultEntryDtoPA {


	public static final CommandResultLineDtoPA INSTANCE = GWT.create(CommandResultLineDtoPA.class);


	/* Properties */
	public ValueProvider<CommandResultLineDto,String> line();


}
