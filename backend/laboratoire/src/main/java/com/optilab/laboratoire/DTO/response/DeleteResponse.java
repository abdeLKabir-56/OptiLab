package com.optilab.laboratoire.DTO.response;

import lombok.Builder;

@Builder
public record DeleteResponse(
        String message
) {
}
