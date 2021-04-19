package net.datenwerke.security.service.security.rights.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.security.client.security.dto.ExecuteDto;
import net.datenwerke.security.service.security.rights.Execute;
import net.datenwerke.security.service.security.rights.dtogen.Execute2DtoGenerator;

/**
 * Poso2DtoGenerator for Execute
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Execute2DtoGenerator implements Poso2DtoGenerator<Execute,ExecuteDto> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public Execute2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public ExecuteDto instantiateDto(Execute poso)  {
		ExecuteDto dto = new ExecuteDto();
		return dto;
	}

	public ExecuteDto createDto(Execute poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final ExecuteDto dto = new ExecuteDto();
		dto.setDtoView(here);

		if(here.compareTo(DtoView.NORMAL) >= 0){
			/*  set Abbreviation */
			dto.setAbbreviation(poso.getAbbreviation() );

			/*  set BitField */
			dto.setBitField(poso.getBitField() );

			/*  set Description */
			dto.setDescription(poso.getDescription() );

		}

		return dto;
	}


}
