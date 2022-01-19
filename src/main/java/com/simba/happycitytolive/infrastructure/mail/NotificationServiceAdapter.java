package com.simba.happycitytolive.infrastructure.mail;

import com.simba.happycitytolive.application.domain.NotificationCadeau;
import com.simba.happycitytolive.application.domain.NotificationService;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by podisto on 19/01/2022.
 */
@Component
public class NotificationServiceAdapter implements NotificationService {

    @Override
    public void sendMailAnnonceAttribution(List<NotificationCadeau> notifications) {

    }

    @Override
    public void sendMailRecapitulatif(List<NotificationCadeau> notifications) {

    }
}
