package com.simba.happycitytolive.application;

import com.simba.happycitytolive.application.dto.HabitantEligible;
import com.simba.happycitytolive.domain.Habitant;
import com.simba.happycitytolive.domain.HabitantRepository;
import lombok.RequiredArgsConstructor;

import java.time.Clock;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by podisto on 15/01/2022.
 */
@RequiredArgsConstructor
public class HabitantServiceImpl implements HabitantService {

    private final HabitantRepository habitantRepository;
    private final Clock clock;

    @Override
    public List<HabitantEligible> retrieveEligibleResidents() {
        LocalDate currentDate = LocalDate.now(clock);
        List<Habitant> habitants = habitantRepository.findByDateArriveeCommuneLessThanAndDateAttributionCadeauIsNullAndCadeauOffertIsFalse(currentDate);
        return getHabitantEligibles(habitants);
    }

    private List<HabitantEligible> getHabitantEligibles(List<Habitant> habitants) {
        return habitants.stream().map(HabitantEligible::new).collect(Collectors.toList());
    }
}
