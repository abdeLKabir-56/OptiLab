package com.dossier_service.dossier_service.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }

    public String generateEmailContent(String recipientName, String notificationType, String notificationDetails) {
        String template = new String(Files.readAllBytes(Paths.get("src/main/resources/templates/email-template.html")), StandardCharsets.UTF_8);
        return template
                .replace("{{recipientName}}", recipientName)
                .replace("{{notificationType}}", notificationType)
                .replace("{{notificationDetails}}", notificationDetails)
                .replace("{{currentYear}}", String.valueOf(LocalDate.now().getYear()));
    }
}
