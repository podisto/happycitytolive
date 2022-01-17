package com.simba.happycitytolive.application.domain;

import java.util.List;
import java.util.Optional;

/**
 * Created by podisto on 16/01/2022.
 */
public interface AttributionCadeauRepository {

    Optional<CadeauAttribue> byHabitant(String email);

    void save(CadeauAttribue cadeauOffert);

    List<CadeauAttribue> all();
}
