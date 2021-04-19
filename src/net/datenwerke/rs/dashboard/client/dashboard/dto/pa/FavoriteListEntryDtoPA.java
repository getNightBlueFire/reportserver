package net.datenwerke.rs.dashboard.client.dashboard.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.Long;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.dashboard.client.dashboard.dto.FavoriteListEntryDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.FavoriteListEntryDtoDec;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.AbstractTsDiskNodeDto;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.dashboard.service.dashboard.dagets.FavoriteListEntry.class)
public interface FavoriteListEntryDtoPA extends PropertyAccess<FavoriteListEntryDto> {


	public static final FavoriteListEntryDtoPA INSTANCE = GWT.create(FavoriteListEntryDtoPA.class);

	@Path("dtoId")
	public ModelKeyProvider<FavoriteListEntryDto> dtoId();

	/* Properties */
	public ValueProvider<FavoriteListEntryDto,Long> id();
	public ValueProvider<FavoriteListEntryDto,AbstractTsDiskNodeDto> referenceEntry();


}
