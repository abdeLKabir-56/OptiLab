package com.dossier_service.dossier_service.kafka;
import com.dossier_service.dossier_service.dto.NotificationDTO;
import com.dossier_service.dossier_service.service.EmailService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
@Slf4j
@Service
public class KafkaConsumerService {

    private final EmailService emailService;

    public KafkaConsumerService(EmailService emailService) {
        this.emailService = emailService;
    }

    @KafkaListener(topics = "notification-topic", groupId = "notification-group")
    public void consume(String message) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            NotificationDTO notification = mapper.readValue(message, NotificationDTO.class);
            emailService.sendEmail(notification.getUserEmail(), "Exam Notification", notification.getMessage());
        } catch (Exception e) {
            log.error("Failed to process notification message: {}", message, e);
        }
    }
}


