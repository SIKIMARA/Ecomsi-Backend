package org.sikimaraservices.notification;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sikimaraservices.clients.notification.NotificationRequest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
@Slf4j
public class NotificationService {

    private JavaMailSender mailSender;

    private final NotificationRepository notificationRepository;

    public void send(NotificationRequest notificationRequest) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom("driss.shi.shi@gmail.com");
            helper.setTo(notificationRequest.getToEmail());
            helper.setSubject("Notification from ECOMSI");
            helper.setText(notificationRequest.getBody(), true); // Set the second parameter to true to indicate HTML content

            mailSender.send(mimeMessage);

            log.info("Notification Sent", notificationRequest);
        } catch (MessagingException e) {
            log.error("Error sending notification", e);
        }
    }
}
