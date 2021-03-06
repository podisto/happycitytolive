package com.simba.happycitytolive.infrastructure.mail;

import com.simba.happycitytolive.application.domain.Notification;
import com.simba.happycitytolive.application.domain.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by podisto on 19/01/2022.
 */
@Component
@Slf4j
public class NotificationServiceAdapter implements NotificationService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private MailProperties properties;

    @Async
    @Override
    public void sendMailAnnonceAttribution(List<Notification> notifications) {
        log.info("envoie mail annonce attribution cadeau");
        notifications.parallelStream().forEach(notification -> send(notification.getEmail(), "[Kaolack] - Joyeux anniversaire", notification.getMessage()));
    }

    @Async
    @Override
    public void sendMailRecapitulatif(List<Notification> notifications) {
        log.info("envoie mail récapitulatif au service Cadeau");
        StringBuilder sb = new StringBuilder();
        for (Notification notification: notifications) {
            sb.append("\n").append(notification.getMessage());
        }
        send(properties.getEmailMairie(), "Récapitulatif des Cadeaux attribués", sb.toString());
    }

    private void send(String to, String subject, String content) {
        log.info("send email to {}", to);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(properties.getFrom());
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);

        this.mailSender.send(message);
    }
}
