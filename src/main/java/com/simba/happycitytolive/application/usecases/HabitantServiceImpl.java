package com.simba.happycitytolive.application.usecases;

import com.simba.happycitytolive.application.domain.DateFormatter;
import com.simba.happycitytolive.application.domain.Habitant;
import com.simba.happycitytolive.application.domain.HabitantRepository;
import com.simba.happycitytolive.application.usecases.dto.HabitantEligible;
import com.simba.happycitytolive.application.usecases.dto.NouvelHabitant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.Clock;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by podisto on 15/01/2022.
 */
@RequiredArgsConstructor
@Slf4j
public class HabitantServiceImpl implements HabitantService {

    private final HabitantRepository habitantRepository;
    private final Clock clock;

    @Override
    public List<HabitantEligible> getEligibleHabitants() {
        LocalDate currentDate = LocalDate.now(clock);
        List<Habitant> habitants = habitantRepository.findEligibleHabitants(currentDate);
        return toDtoList(habitants);
    }

    @Override
    public void addHabitant(NouvelHabitant nouvelHabitant) {
        Habitant habitant = new Habitant(nouvelHabitant.getNom(), nouvelHabitant.getPrenom(), nouvelHabitant.getEmail(),
                DateFormatter.toDate(nouvelHabitant.getDateNaissance()), DateFormatter.toDate(nouvelHabitant.getDateArriveeCommune()), nouvelHabitant.getAdresse());
        habitantRepository.save(habitant);
    }

    private List<HabitantEligible> toDtoList(List<Habitant> habitants) {
        return habitants.stream().map(this::toDto).collect(Collectors.toList());
    }

    private HabitantEligible toDto(Habitant habitant) {
        HabitantEligible habitantEligible = new HabitantEligible();
        habitantEligible.setNom(habitant.getNom());
        habitantEligible.setPrenom(habitant.getPrenom());
        habitantEligible.setDateNaissance(DateFormatter.toString(habitant.getDateNaissance()));
        habitantEligible.setEmail(habitant.getEmail());
        habitantEligible.setDateArriveeCommune(DateFormatter.toString(habitant.getDateArriveeCommune()));
        habitantEligible.setAdresse(habitant.getAdresse());
        return habitantEligible;
    }
}
