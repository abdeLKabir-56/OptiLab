package com.optilab.utilisateur.services;

import com.optilab.utilisateur.exception.TechnicalException;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
@RequiredArgsConstructor
@Slf4j
public class MailSenderService {

    @Value("${spring.mail.username}")
    private String username;
    @Value("${spring.mail.password}")
    private String gmailPassword;
    @Value("${spring.mail.host}")
    private String host;
    @Value("${spring.mail.port}")
    private String port;

    public void sendCredentialsEmail(String email, String password) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, gmailPassword);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject("Your Account Credentials");
            message.setText("Dear User,\n\n"
                    + "Your account has been successfully created.\n"
                    + "Here are your credentials:\n"
                    + "Email: " + email + "\n"
                    + "Password: " + password + "\n\n"
                    + "Please keep your credentials secure.\n\n"
                    + "Best regards,\n"
                    + "Optilab Team");

            Transport.send(message);
            log.info("Credentials email sent to: {}", email);
        } catch (MessagingException e) {
            log.error("Failed to send email to: {}", email, e);
            throw new TechnicalException("Failed to send email");
        }
    }

}
