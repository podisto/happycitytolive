package com.simba.happycitytolive.application;

import com.simba.happycitytolive.application.dto.HabitantEligible;

import java.util.List;

/**
 * Created by podisto on 15/01/2022.
 */
public interface HabitantService {

    List<HabitantEligible> retrieveEligibleResidents();
}
