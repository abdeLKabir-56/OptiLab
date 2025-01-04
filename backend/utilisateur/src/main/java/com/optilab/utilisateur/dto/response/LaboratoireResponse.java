package com.optilab.utilisateur.dto.response;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record LaboratoireResponse(
        Long id,
        String nom,
        String logo,
        Long nrc,
        boolean active,
        LocalDate dateActivation,
        Long adresseId,
        Long contactLaboratoirId
) {
}
