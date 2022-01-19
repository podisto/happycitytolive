package com.simba.happycitytolive.infrastructure.rest;

import com.simba.happycitytolive.application.usecases.HabitantService;
import com.simba.happycitytolive.application.usecases.dto.HabitantEligible;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by podisto on 15/01/2022.
 */
@RestController
@RequestMapping(value = "/habitants")
@RequiredArgsConstructor
public class HabitantResource {

    private final HabitantService habitantService;

    @GetMapping
    public ResponseEntity<List<HabitantEligible>> getEligibleResidents() {
        List<HabitantEligible> eligibleResidents = habitantService.getEligibleHabitants();
        return ResponseEntity.ok(eligibleResidents);
    }
}
