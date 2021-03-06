package com.simba.happycitytolive.application.usecases;

import com.simba.happycitytolive.application.domain.CadeauHabitantRepository;
import com.simba.happycitytolive.application.domain.CadeauHabitant;
import com.simba.happycitytolive.application.domain.Notification;
import com.simba.happycitytolive.application.domain.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.Clock;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by podisto on 19/01/2022.
 */
@RequiredArgsConstructor
@Slf4j
public class MailerServiceImpl implements MailerService {

    private final CadeauHabitantRepository cadeauHabitantRepository;
    private final NotificationService notificationService;
    private final Clock clock;

    @Override
    public void sendMailReport() {
        LocalDate now = LocalDate.now(clock);
        List<CadeauHabitant> cadeauxOfferts = cadeauHabitantRepository.allDistributedGiftsByDay(now);
        if (!cadeauxOfferts.isEmpty()) {
            List<Notification> notifications = cadeauxOfferts.stream()
                    .map(c -> new Notification(c.getNom(), c.getPrenom(), c.getEmail(), c.getDetails()))
                    .collect(Collectors.toList());
            notificationService.sendMailRecapitulatif(notifications);
        } else {
            log.info("Aucun cadeau attribué de la journée.");
        }
    }
}
