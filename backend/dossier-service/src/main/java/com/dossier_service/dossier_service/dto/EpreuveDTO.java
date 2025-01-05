package com.dossier_service.dossier_service.dto;

import com.dossier_service.dossier_service.entity.Analyse;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class EpreuveDTO {
    private Long id;
    private String  fkIdAnalyse;
    private String nom;
    private Analyse analyse;
}
