package com.dossier_service.dossier_service.dto;

import lombok.Data;

@Data
public class NotificationDTO {
    private String recipient;
    private String message;
    private String notificationType;
}
