package com.simba.happycitytolive.infrastructure.inmemory;

import com.simba.happycitytolive.application.domain.Habitant;
import com.simba.happycitytolive.application.domain.HabitantRepository;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by podisto on 15/01/2022.
 */
@Slf4j
public class InMemoryHabitantRepository implements HabitantRepository {

    private final List<Habitant> listeHabitants = new LinkedList<>();

    @Override
    public void save(Habitant habitant) {
        if (habitant.getId() == null) {
            habitant.setId(UUID.randomUUID().toString());
            listeHabitants.add(habitant);
        } else {
            listeHabitants.stream()
                    .filter(h -> h.getId().equals(habitant.getId()))
                    .findAny()
                    .ifPresent(found -> found.setCadeauOffert(habitant.isCadeauOffert()));
        }
    }

    // TODO externaliser la valeur year
    @Override
    public List<Habitant> findEligibleHabitants(LocalDate currentDate) {
        return listeHabitants.stream()
                .filter(habitant -> habitant.isDateArriveeCommuneGreaterThanAYear(currentDate, 1))
                .collect(Collectors.toList());
    }
}
