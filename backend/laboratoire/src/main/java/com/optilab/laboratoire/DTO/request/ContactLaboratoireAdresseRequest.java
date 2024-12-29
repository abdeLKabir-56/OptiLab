package com.optilab.laboratoire.DTO.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ContactLaboratoireAdresseRequest {

    private Long numVoie;
    private String nomVoie;
    private Long codePostal;
    private String ville;
    private String commune;
    private Long laboratoireId;
    private Long numTel;
    private Long fax;
    private String email;

}
