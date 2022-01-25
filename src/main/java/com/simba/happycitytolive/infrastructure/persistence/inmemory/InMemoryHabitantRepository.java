package com.simba.happycitytolive.infrastructure.persistence.inmemory;

import com.simba.happycitytolive.application.domain.Habitant;
import com.simba.happycitytolive.application.domain.HabitantRepository;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by podisto on 15/01/2022.
 */
@Slf4j
public class InMemoryHabitantRepository implements HabitantRepository {

    private final List<Habitant> listeHabitants = new ArrayList<>();

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

    @Override
    public List<Habitant> findEligibleHabitants(LocalDate currentDate) {
        return listeHabitants.stream()
                .filter(habitant -> habitant.isDateArriveeCommuneGreaterThanAYear(currentDate, 1))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Habitant> byEmail(String email) {
        return listeHabitants.stream().filter(h -> h.getEmail().equals(email)).findAny();
    }

    @Override
    public List<Habitant> all() {
        return listeHabitants;
    }
}
