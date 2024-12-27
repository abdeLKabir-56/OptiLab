package com.dossier_service.dossier_service.dto;

import com.dossier_service.dossier_service.entity.Analyse;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class EpreuveDTO {
    private String  fkIdAnalyse;
    private String nom;
    private Analyse analyse;
}
