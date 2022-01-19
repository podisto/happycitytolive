package com.simba.happycitytolive.application.usecases;

import com.simba.happycitytolive.application.domain.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.Clock;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by podisto on 16/01/2022.
 */
@RequiredArgsConstructor
@Slf4j
public class AttributionCadeauxServiceImpl implements AttributionCadeauService {

    private final AttributionCadeauRepository attributionCadeauRepository;
    private final CadeauRepository cadeauRepository;
    private final HabitantRepository habitantRepository;
    private final NotificationService notificationService;
    private final Clock clock;

    @Override
    public void attribuer() {
        LocalDate now = LocalDate.now(clock);
        List<Habitant> habitants = habitantRepository.findEligibleHabitants(now);
        List<Cadeau> cadeauxByTrancheAge = getCadeauxByTrancheAge(habitants);
        List<NotificationCadeau> recipients = new ArrayList<>();
        for (Cadeau cadeau: cadeauxByTrancheAge) {
            for (Habitant habitant: habitants) {
                if (habitant.ageBetween(cadeau.getTrancheAge()) && habitant.hasNoCadeauOffert()) {
                    CadeauAttribue cadeauOffert = attribuerCadeau(cadeau, habitant);
                    habitantRepository.save(habitant);
                    recipients.add(new NotificationCadeau(habitant, cadeauOffert.getDetails()));
                }
            }
        }
        notificationService.sendMailAnnonceAttribution(recipients);
    }

    private CadeauAttribue attribuerCadeau(Cadeau cadeau, Habitant habitant) {
        CadeauAttribue cadeauOffert = new CadeauAttribue(habitant, cadeau);
        attributionCadeauRepository.save(cadeauOffert);
        habitant.setCadeauOffert(true);
        return cadeauOffert;
    }

    private List<Cadeau> getCadeauxByTrancheAge(List<Habitant> habitants) {
        List<Cadeau> cadeauxByTrancheAge = new ArrayList<>();
        for (Habitant habitant: habitants) {
            List<Cadeau> cadeaux = cadeauRepository.byTrancheAge(habitant);
            cadeauxByTrancheAge.addAll(cadeaux);
        }
        return cadeauxByTrancheAge;
    }

}
