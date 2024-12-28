package com.dossier_service.dossier_service.kafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "notification-topic", groupId = "notification-service-group")
    public void consumeMessage(String message) {
        System.out.println("Received message: " + message);
    }
}


