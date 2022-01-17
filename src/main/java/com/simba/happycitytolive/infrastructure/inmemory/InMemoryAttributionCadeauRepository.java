package com.simba.happycitytolive.infrastructure.inmemory;

import com.simba.happycitytolive.application.domain.AttributionCadeauRepository;
import com.simba.happycitytolive.application.domain.CadeauAttribue;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by podisto on 16/01/2022.
 */
@Slf4j
public class InMemoryAttributionCadeauRepository implements AttributionCadeauRepository {

    private final List<CadeauAttribue> cadeauOfferts = new ArrayList<>();

    @Override
    public Optional<CadeauAttribue> byHabitant(String email) {
        return cadeauOfferts.stream().filter(cadeau -> cadeau.getEmail().equals(email)).findAny();
    }

    @Override
    public void save(CadeauAttribue cadeauOffert) {
        cadeauOfferts.add(cadeauOffert);
    }

    @Override
    public List<CadeauAttribue> all() {
        return cadeauOfferts;
    }
}
