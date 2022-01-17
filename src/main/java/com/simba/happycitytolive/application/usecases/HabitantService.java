package com.simba.happycitytolive.application.usecases;

import com.simba.happycitytolive.application.usecases.dto.HabitantEligible;
import com.simba.happycitytolive.application.usecases.dto.NouvelHabitant;

import java.util.List;

/**
 * Created by podisto on 15/01/2022.
 */
public interface HabitantService {

    List<HabitantEligible> getEligibleResidents();

    void addResident(NouvelHabitant nouvelHabitant);
}
