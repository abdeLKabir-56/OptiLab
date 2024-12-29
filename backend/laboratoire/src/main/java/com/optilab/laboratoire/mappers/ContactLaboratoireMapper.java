package com.optilab.laboratoire.mappers;

import com.optilab.laboratoire.DTO.request.ContactLaboratoireAdresseRequest;
import com.optilab.laboratoire.DTO.request.ContactLaboratoireRequest;
import com.optilab.laboratoire.DTO.response.ContactLaboratoireResponse;
import com.optilab.laboratoire.entities.ContactLaboratoire;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ContactLaboratoireMapper {

    ContactLaboratoireMapper INSTANCE = Mappers.getMapper(ContactLaboratoireMapper.class);
    ContactLaboratoire toEntity(ContactLaboratoireRequest contactLaboratoireRequest);
    @Mapping(target = "ville", source = "contactLaboratoire.adresse.ville")
    @Mapping(target = "laboName", source = "contactLaboratoire.laboratoire.nom")
    ContactLaboratoireResponse toResponse(ContactLaboratoire contactLaboratoire);
    ContactLaboratoireRequest toRequest(ContactLaboratoireAdresseRequest contactLaboratoireAdresseRequest);
}
