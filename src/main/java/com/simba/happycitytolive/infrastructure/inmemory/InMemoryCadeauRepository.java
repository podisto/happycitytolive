package com.simba.happycitytolive.infrastructure.inmemory;

import com.simba.happycitytolive.application.domain.Cadeau;
import com.simba.happycitytolive.application.domain.CadeauRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by podisto on 17/01/2022.
 */
public class InMemoryCadeauRepository implements CadeauRepository {

    private final List<Cadeau> cadeaux = new ArrayList<>();

    @Override
    public List<Cadeau> byTrancheAge(int age) {
        return cadeaux.stream()
                .filter(cadeau -> cadeau.getTrancheAge().isBetween(age))
                .collect(Collectors.toList());
    }

    @Override
    public void save(Cadeau cadeau) {
        cadeaux.add(cadeau);
    }
}
