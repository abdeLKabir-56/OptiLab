package com.optilab.laboratoire.mappers;

import com.optilab.laboratoire.DTO.request.AdresseRequest;
import com.optilab.laboratoire.DTO.request.ContactLaboratoireAdresseRequest;
import com.optilab.laboratoire.DTO.request.ContactLaboratoireRequest;
import com.optilab.laboratoire.DTO.response.AdresseResponse;
import com.optilab.laboratoire.entities.Adresse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AdresseMapper {

    AdresseMapper INSTANCE = Mappers.getMapper(AdresseMapper.class);
    Adresse toEntity(AdresseRequest adresseRequest);
    AdresseResponse toResponse(Adresse adresse);
    Adresse toEntityFromResponse(AdresseResponse adresseResponse);
    AdresseRequest toRequest(ContactLaboratoireAdresseRequest contactLaboratoireAdresseRequest);
}
