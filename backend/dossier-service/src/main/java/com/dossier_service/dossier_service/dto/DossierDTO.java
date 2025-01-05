package com.dossier_service.dossier_service.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DossierDTO {
    private Long id;
    private String fkEmailUtilisateur;
    private String  fkIdPatient;
    private LocalDate date;
    private String status;
}
