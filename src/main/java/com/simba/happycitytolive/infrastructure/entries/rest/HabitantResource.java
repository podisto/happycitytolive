package com.simba.happycitytolive.infrastructure.entries.rest;

import com.simba.happycitytolive.application.usecases.HabitantService;
import com.simba.happycitytolive.application.usecases.dto.HabitantEligible;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by podisto on 15/01/2022.
 */
@RestController
@RequestMapping(value = "/api/habitants")
@RequiredArgsConstructor
@Slf4j
public class HabitantResource {

    private final HabitantService habitantService;

    @GetMapping("/eligibles")
    public ResponseEntity<List<HabitantEligible>> getEligibleResidents() {
        log.info("Récupération Habitants éligibles");
        List<HabitantEligible> eligibleResidents = habitantService.getEligibleHabitants();
        return ResponseEntity.ok(eligibleResidents);
    }
}
