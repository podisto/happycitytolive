package com.simba.happycitytolive.infrastructure.inmemory;

import com.simba.happycitytolive.application.domain.Habitant;
import com.simba.happycitytolive.application.domain.HabitantRepository;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by podisto on 15/01/2022.
 */
public class InMemoryHabitantRepository implements HabitantRepository {

    private final List<Habitant> listeHabitants = new LinkedList<>();

    @Override
    public void save(Habitant habitant) {
        listeHabitants.add(habitant);
    }

    @Override
    public List<Habitant> findByDateArriveeCommuneLessThanAndDateAttributionCadeauIsNullAndCadeauOffertIsFalse(LocalDate dateCourante) {
        return listeHabitants.stream()
                .filter(habitant -> habitant.isDateArriveeCommuneGreaterThanAYear(dateCourante, 1))
                .filter(Habitant::hasNoCadeauOffert)
                .collect(Collectors.toList());
    }
}
