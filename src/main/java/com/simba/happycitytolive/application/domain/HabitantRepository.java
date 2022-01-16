package com.simba.happycitytolive.application.domain;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by podisto on 15/01/2022.
 */
public interface HabitantRepository {

    void save(Habitant habitant);

    List<Habitant> findByDateArriveeCommuneLessThanAndDateAttributionCadeauIsNullAndCadeauOffertIsFalse(LocalDate dateCourante);
}
