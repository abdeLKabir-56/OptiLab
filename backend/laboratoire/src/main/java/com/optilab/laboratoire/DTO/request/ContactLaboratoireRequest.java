package com.optilab.laboratoire.DTO.request;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ContactLaboratoireRequest {
    private Long id;
    private Long laboratoireId;    // ID of the Labo entity
    private Long adresseId;    // ID of the Adresse entity
    private Long numTel;
    private Long fax;
    private String email;
}
