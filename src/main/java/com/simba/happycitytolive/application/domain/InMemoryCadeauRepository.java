package com.simba.happycitytolive.application.domain;

import java.util.List;
import java.util.Optional;

/**
 * Created by podisto on 16/01/2022.
 */
public class InMemoryCadeauRepository implements CadeauRepository {

    @Override
    public Optional<Cadeau> byEmail(String email) {
        return Optional.empty();
    }

    @Override
    public List<Cadeau> byTrancheAge(int min, int max) {
        return null;
    }
}
