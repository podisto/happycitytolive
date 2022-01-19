package com.simba.happycitytolive.application.usecases;

import com.simba.happycitytolive.application.domain.AttributionCadeauRepository;
import com.simba.happycitytolive.application.domain.CadeauAttribue;
import com.simba.happycitytolive.application.domain.NotificationCadeau;
import com.simba.happycitytolive.application.domain.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.Clock;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by podisto on 19/01/2022.
 */
@RequiredArgsConstructor
@Slf4j
public class EnvoieMailServiceImpl implements EnvoieMailService {

    private final AttributionCadeauRepository attributionCadeauRepository;
    private final NotificationService notificationService;
    private final Clock clock;

    @Override
    public void sendMailReport() {
        LocalDate now = LocalDate.now(clock);
        List<CadeauAttribue> cadeauxOfferts = attributionCadeauRepository.allDistributedGiftsByDay(now);
        List<NotificationCadeau> notifications = new ArrayList<>();
        for (CadeauAttribue cadeauOffert: cadeauxOfferts) {
            notifications.add(new NotificationCadeau(cadeauOffert.getHabitant(), cadeauOffert.getDetails()));
        }
        notificationService.sendMailRecapitulatif(notifications);
    }
}
