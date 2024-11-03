package com.optilab.laboratoire;


import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public record LaboratoireRequest(
        Long id,
        @NotNull(message = "Laboratory name is mandatory")
        String nom,
        @NotNull(message = "Laboratory logo is mandatory")
        String logo,
        @NotNull(message = "Laboratory nrc is mandatory")
        String nrc,
        boolean isActive,
        LocalDate dateActivation
) {
}