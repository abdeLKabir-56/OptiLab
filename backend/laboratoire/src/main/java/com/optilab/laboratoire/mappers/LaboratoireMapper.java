package com.optilab.laboratoire.mappers;

import com.optilab.laboratoire.DTO.request.LaboratoireRequest;
import com.optilab.laboratoire.DTO.response.LaboratoireResponse;
import com.optilab.laboratoire.entities.Laboratoire;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LaboratoireMapper {

    LaboratoireMapper INSTANCE = Mappers.getMapper(LaboratoireMapper.class);

    Laboratoire toEntity(LaboratoireRequest laboratoireRequest);

    LaboratoireResponse toResponse(Laboratoire laboratoire);

    Laboratoire toEntityFromResponse(LaboratoireResponse laboratoireResponse);
}
