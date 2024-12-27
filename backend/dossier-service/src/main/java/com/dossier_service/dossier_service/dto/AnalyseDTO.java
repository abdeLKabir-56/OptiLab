package com.dossier_service.dossier_service.dto;

import com.dossier_service.dossier_service.entity.Epreuve;


import java.util.List;
import java.util.UUID;

public class AnalyseDTO {
    private String nom;
    private String description;
    private String  fkIdLaboratoire;
    private List<String> epreuves;
}
