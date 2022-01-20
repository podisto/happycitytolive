package com.simba.happycitytolive.application.domain;

import java.util.List;

/**
 * Created by podisto on 17/01/2022.
 */
public interface NotificationService {

    void sendMailAnnonceAttribution(List<Notification> notifications);

    void sendMailRecapitulatif(List<Notification> notifications);
}
