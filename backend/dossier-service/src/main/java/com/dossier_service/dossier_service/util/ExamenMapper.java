package com.dossier_service.dossier_service.util;
import com.dossier_service.dossier_service.dto.ExamenDTO;
import com.dossier_service.dossier_service.entity.Examen;
import org.springframework.stereotype.Component;

@Component
public class ExamenMapper implements Mapper<Examen, ExamenDTO> {

    @Override
    public ExamenDTO toDto(Examen entity) {
        if (entity == null) {
            return null;
        }

        ExamenDTO dto = new ExamenDTO();
        dto.setId(entity.getId());
        dto.setDossier(entity.getDossier());
        dto.setEpreuve(entity.getEpreuve());
        dto.setResultat(entity.getResultat());
        dto.setObservations(entity.getObservations());
        return dto;
    }

    @Override
    public Examen toEntity(ExamenDTO dto) {
        if (dto == null) {
            return null;
        }

        Examen examen = new Examen();
        examen.setId(dto.getId());

        // The Dossier and Epreuve should be fetched from the database in the service layer
        // and set here before persisting the entity.

        examen.setResultat(dto.getResultat());
        examen.setObservations(dto.getObservations());
        return examen;
    }
}
