package com.simba.happycitytolive.application.usecases;

import com.simba.happycitytolive.application.usecases.dto.HabitantEligible;

import java.util.List;

/**
 * Created by podisto on 16/01/2022.
 */
public interface AttributionCadeauService {

    void attribuerCadeaux(List<HabitantEligible> habitants);
}
