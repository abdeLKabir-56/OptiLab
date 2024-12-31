package com.dossier_service.dossier_service.dto;

import com.dossier_service.dossier_service.entity.Dossier;
import com.dossier_service.dossier_service.entity.Epreuve;
import jakarta.persistence.*;
import lombok.Data;

@Data
public class ExamenDTO {
    private Long id;
    private String resultat;
    private String observations;
    private Epreuve epreuve;
}
