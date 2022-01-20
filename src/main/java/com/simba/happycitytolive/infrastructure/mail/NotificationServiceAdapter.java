package com.simba.happycitytolive.infrastructure.mail;

import com.simba.happycitytolive.application.domain.NotificationCadeau;
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
    public void sendMailAnnonceAttribution(List<NotificationCadeau> notifications) {
        log.info("envoie mail annonce attribution cadeau");
        notifications.parallelStream().forEach(notification -> send(notification.getEmail(), "[Kaolack] - Joyeux anniversaire", notification.getCadeau()));
    }

    @Async
    @Override
    public void sendMailRecapitulatif(List<NotificationCadeau> notifications) {
        log.info("envoie mail récapitulatif");
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
