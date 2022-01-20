package com.simba.happycitytolive.application.domain;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Created by podisto on 16/01/2022.
 */
public interface AttributionCadeauRepository {

    Optional<CadeauHabitant> byHabitant(String email);

    void save(CadeauHabitant cadeauOffert);

    List<CadeauHabitant> all();

    List<CadeauHabitant> allDistributedGiftsByDay(LocalDate dateAttribution);
}
