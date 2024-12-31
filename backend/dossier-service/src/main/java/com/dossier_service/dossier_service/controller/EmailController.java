package com.dossier_service.dossier_service.controller;

import com.dossier_service.dossier_service.service.EmailService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    /*@GetMapping("/send-email")
    public String sendEmail() {
        String emailContent = emailService.generateEmailContent("abdelkabir Elhamoussi", "Exam Result", "Your exam results are now available.");
        emailService.sendEmail("abdelkabirelhamoussi@gmail.com", "Exam Notification", emailContent);
        return "Email Sent!";
    }*/
}

