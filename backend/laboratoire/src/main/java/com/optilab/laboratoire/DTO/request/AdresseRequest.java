package com.optilab.laboratoire.DTO.request;

import lombok.*;

@Data @AllArgsConstructor @NoArgsConstructor
public class AdresseRequest {
    private Long id;
    private Long numVoie;
    private String nomVoie;
    private Long codePostal;
    private String ville;
    private String commune;
    private Long contactLaboratoireId;
}
