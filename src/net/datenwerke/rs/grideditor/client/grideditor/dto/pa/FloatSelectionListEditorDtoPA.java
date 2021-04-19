package net.datenwerke.rs.grideditor.client.grideditor.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.util.List;
import java.util.Map;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.grideditor.client.grideditor.dto.FloatSelectionListEditorDto;
import net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.FloatSelectionListEditorDtoDec;
import net.datenwerke.rs.grideditor.client.grideditor.dto.pa.SelectionListEditorDtoPA;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.grideditor.service.grideditor.definition.editor.FloatSelectionListEditor.class)
public interface FloatSelectionListEditorDtoPA extends SelectionListEditorDtoPA {


	public static final FloatSelectionListEditorDtoPA INSTANCE = GWT.create(FloatSelectionListEditorDtoPA.class);


	/* Properties */
	public ValueProvider<FloatSelectionListEditorDto,Map<String, Float>> valueMap();
	public ValueProvider<FloatSelectionListEditorDto,List<Float>> values();


}
