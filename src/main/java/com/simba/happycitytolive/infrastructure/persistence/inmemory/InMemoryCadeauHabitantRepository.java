package com.simba.happycitytolive.infrastructure.persistence.inmemory;

import com.simba.happycitytolive.application.domain.CadeauHabitantRepository;
import com.simba.happycitytolive.application.domain.CadeauHabitant;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by podisto on 16/01/2022.
 */
@Slf4j
public class InMemoryCadeauHabitantRepository implements CadeauHabitantRepository {

    private final List<CadeauHabitant> cadeauxOfferts = new ArrayList<>();

    @Override
    public Optional<CadeauHabitant> byHabitant(String email) {
        return cadeauxOfferts.stream().filter(cadeau -> cadeau.getEmail().equals(email)).findAny();
    }

    @Override
    public void save(CadeauHabitant cadeauOffert) {
        cadeauxOfferts.add(cadeauOffert);
    }

    @Override
    public List<CadeauHabitant> all() {
        return cadeauxOfferts;
    }

    @Override
    public List<CadeauHabitant> allDistributedGiftsByDay(LocalDate dateAttribution) {
        return cadeauxOfferts.stream()
                .filter(c -> c.getDateAttribution().isEqual(dateAttribution))
                .collect(Collectors.toList());
    }
}
