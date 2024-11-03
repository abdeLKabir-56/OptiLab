package com.optilab.laboratoire;

import java.time.LocalDate;

public record LaboratoireResponse(
        Long id,
        String nom,
        String logo,
        String nrc,
        Boolean isActive,
        LocalDate dateActivation
) {
}
