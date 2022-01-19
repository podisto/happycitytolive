package com.simba.happycitytolive.application.domain;

import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by podisto on 17/01/2022.
 */
@Slf4j
public class FakeNotification implements NotificationService {

    private final Map<String, List<String>> emails = new HashMap<>();

    @Override
    public void sendMail(List<NotificationCadeau> notifications) {
        notifications.forEach(notification -> emails.put(notification.getEmail(), Collections.singletonList(notification.getCadeau())));
    }

    @Override
    public List<String> byEmail(String email) {
        return emails.get(email);
    }
}