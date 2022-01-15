package com.simba.happycitytolive.domain;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by podisto on 15/01/2022.
 */
public interface HabitantRepository {
    void add(List<Habitant> habitants);

    List<Habitant> findByDateArriveeCommuneLessThanAndDateAttributionCadeauIsNullAndCadeauOffertIsFalse(LocalDate dateCourante);
}
