package com.simba.happycitytolive.infrastructure.mail;

import com.simba.happycitytolive.application.domain.NotificationCadeau;
import com.simba.happycitytolive.application.domain.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by podisto on 19/01/2022.
 */
@Component
@Slf4j
public class NotificationServiceAdapter implements NotificationService {

    @Async
    @Override
    public void sendMailAnnonceAttribution(List<NotificationCadeau> notifications) {
        log.info("envoie mail annonce attribution cadeau");
        notifications.forEach(notification -> log.info("Envoi mail à {} {}: {}", notification.getPrenom(), notification.getNom(), notification.getCadeau()));
    }

    @Override
    public void sendMailRecapitulatif(List<NotificationCadeau> notifications) {
        log.info("envoie mail récapitulatif");
    }
}
