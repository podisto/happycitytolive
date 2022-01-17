package com.simba.happycitytolive.application.domain;

import java.util.List;

/**
 * Created by podisto on 17/01/2022.
 */
public interface NotificationService {

    void sendMail(Habitant habitant, CadeauAttribue cadeauOffert);

    List<String> byEmail(String email);
}
