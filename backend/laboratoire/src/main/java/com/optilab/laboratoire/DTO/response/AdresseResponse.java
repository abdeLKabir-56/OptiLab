package com.optilab.laboratoire.DTO.response;

import lombok.Builder;

@Builder
public record AdresseResponse(
        Long id,
        Long numVoie,
        String nomVoie,
        Long codePostal,
        String ville,
        String commune,
        String email
) {
}
