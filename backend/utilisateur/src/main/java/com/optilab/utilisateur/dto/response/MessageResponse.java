package com.optilab.utilisateur.dto.response;

import lombok.Builder;

@Builder
public record MessageResponse(
        String message
) {
}
