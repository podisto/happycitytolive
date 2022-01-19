package com.simba.happycitytolive.infrastructure.persistence.inmemory;

import com.simba.happycitytolive.application.domain.AttributionCadeauRepository;
import com.simba.happycitytolive.application.domain.CadeauAttribue;
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
public class InMemoryAttributionCadeauRepository implements AttributionCadeauRepository {

    private final List<CadeauAttribue> cadeauxOfferts = new ArrayList<>();

    @Override
    public Optional<CadeauAttribue> byHabitant(String email) {
        return cadeauxOfferts.stream().filter(cadeau -> cadeau.getEmail().equals(email)).findAny();
    }

    @Override
    public void save(CadeauAttribue cadeauOffert) {
        cadeauxOfferts.add(cadeauOffert);
    }

    @Override
    public List<CadeauAttribue> all() {
        return cadeauxOfferts;
    }

    @Override
    public List<CadeauAttribue> allDistributedGiftsByDay(LocalDate dateAttribution) {
        return cadeauxOfferts.stream()
                .filter(c -> c.getDateAttribution().isEqual(dateAttribution))
                .collect(Collectors.toList());
    }
}
