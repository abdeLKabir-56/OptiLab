package com.optilab.utilisateur.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ApiErrorResponse (
        String message,
        LocalDateTime timestamp,
        String path,
        String rootUrl,
        Integer status
) {
    public ApiErrorResponse(String message, String path, String rootUrl, Integer errorCode) {
        this(message, LocalDateTime.now(), path, rootUrl, errorCode);
    }
}
