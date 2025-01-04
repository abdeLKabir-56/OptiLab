package com.optilab.utilisateur.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;

@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public record LoginResponse(
        String accessToken,
        Long expiresIn,
        String refreshToken,
        Long refreshExpiresIn,
        String tokenType
) {
}
