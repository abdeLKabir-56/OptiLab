package com.mehdi.optilab.laboratoire;

import java.time.LocalDate;

public record LaboratoireResponse(
        Long id,
        String nom,
        String logo,
        String nrc,
        boolean isActive,
        LocalDate dateActivation
) {
}
