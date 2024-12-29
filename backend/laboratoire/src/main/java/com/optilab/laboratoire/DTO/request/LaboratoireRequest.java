package com.optilab.laboratoire.DTO.request;

import lombok.*;
import java.time.LocalDate;

@Data @AllArgsConstructor @NoArgsConstructor
public class LaboratoireRequest {
    private Long id;
    private String nom;
    private String logo;
    private Long nrc;
    private boolean active;
    private LocalDate dateActivation;
}
