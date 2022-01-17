package com.simba.happycitytolive.application.domain;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * Created by podisto on 17/01/2022.
 */
@Slf4j
public class FakeNotification implements NotificationService {

    private final Map<String, List<String>> emails = new HashMap<>();

    @Override
    public void sendMail(Habitant habitant, CadeauAttribue cadeauOffert) {
        emails.put(habitant.getEmail(), Collections.singletonList(cadeauOffert.getDetails()));
    }

    @Override
    public List<String> byEmail(String email) {
        return emails.get(email);
    }
}
