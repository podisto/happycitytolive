package com.simba.happycitytolive.application.usecases;

import com.simba.happycitytolive.application.domain.*;
import com.simba.happycitytolive.application.usecases.dto.HabitantEligible;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by podisto on 16/01/2022.
 */
@RequiredArgsConstructor
@Slf4j
public class AttributionCadeauxServiceImpl implements AttributionCadeauService {

    private final AttributionCadeauRepository attributionCadeauRepository;
    private final CadeauRepository cadeauRepository;
    private final HabitantRepository habitantRepository;

    @Override
    public void attribuerCadeaux(List<HabitantEligible> habitantsEligibles) {
        List<Habitant> habitants = toListHabitant(habitantsEligibles);
        List<Cadeau> cadeauxByTrancheAge = getCadeauxByTrancheAge(habitants);
        for (Cadeau cadeau: cadeauxByTrancheAge) {
            for (Habitant habitant: habitants) {
                if (cadeau.getTrancheAge().isBetween(habitant.getAge()) && habitant.hasNoCadeauOffert()) {
                    CadeauAttribue cadeauOffert = new CadeauAttribue(habitant, cadeau);
                    habitant.setCadeauOffert(true);
                    attributionCadeauRepository.save(cadeauOffert);
                    habitantRepository.save(habitant);
                }
            }
        }
    }

    private List<Habitant> toListHabitant(List<HabitantEligible> habitantsEligibles) {
        return habitantsEligibles.stream()
                .map(h -> new Habitant(h.getNom(), h.getPrenom(), h.getEmail(), h.getDateNaissance(), h.getDateArriveeCommune(), h.getAdresse()))
                .collect(Collectors.toList());
    }

    private List<Cadeau> getCadeauxByTrancheAge(List<Habitant> habitants) {
        List<Cadeau> cadeauxByTrancheAge = new ArrayList<>();
        for (Habitant habitant: habitants) {
            List<Cadeau> cadeaux = cadeauRepository.byTrancheAge(habitant.getAge());
            cadeauxByTrancheAge.addAll(cadeaux);
        }
        return cadeauxByTrancheAge;
    }

}
