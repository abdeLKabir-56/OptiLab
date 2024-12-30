package com.optilab.laboratoire.DTO.request;

import lombok.*;
import java.time.LocalDate;

@Data @AllArgsConstructor @NoArgsConstructor
public class LaboratoireRequest {
    private String nom;
    private String logo;
    private Long nrc;
    private boolean active;
    private LocalDate dateActivation;
    private Long numVoie;
    private String nomVoie;
    private Long codePostal;
    private String ville;
    private String commune;
    private Long numTel;
    private Long fax;
    private String email;
}
