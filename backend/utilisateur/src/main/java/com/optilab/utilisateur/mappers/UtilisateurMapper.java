package com.optilab.utilisateur.mappers;

import com.optilab.utilisateur.dto.request.UtilisateurRequest;
import com.optilab.utilisateur.dto.response.UtilisateurResponse;
import com.optilab.utilisateur.entities.Utilisateur;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UtilisateurMapper {

    UtilisateurMapper MAPPER = Mappers.getMapper(UtilisateurMapper.class);
    Utilisateur toEntity (UtilisateurRequest utilisateurRequest);
    UtilisateurResponse toResponse (Utilisateur utilisateur);
}
