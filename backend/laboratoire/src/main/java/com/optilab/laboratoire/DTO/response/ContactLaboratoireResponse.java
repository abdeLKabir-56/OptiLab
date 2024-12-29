package com.optilab.laboratoire.DTO.response;

import lombok.Builder;

@Builder
public record ContactLaboratoireResponse(
        Long id,
        String laboName,
        Long numTel,
        Long fax,
        String email,
        String ville
) {
}
