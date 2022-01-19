package com.simba.happycitytolive.infrastructure.persistence.inmemory;

import com.simba.happycitytolive.application.domain.Cadeau;
import com.simba.happycitytolive.application.domain.CadeauRepository;
import com.simba.happycitytolive.application.domain.Habitant;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by podisto on 17/01/2022.
 */
public class InMemoryCadeauRepository implements CadeauRepository {

    private final List<Cadeau> cadeaux = new ArrayList<>();

    @Override
    public List<Cadeau> byTrancheAge(Habitant habitant) {
        return cadeaux.stream()
                .filter(cadeau -> habitant.ageBetween(cadeau.getTrancheAge()))
                .collect(Collectors.toList());
    }

    @Override
    public void save(Cadeau cadeau) {
        cadeaux.add(cadeau);
    }
}
