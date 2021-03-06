package com.simba.happycitytolive.application.usecases;

import com.simba.happycitytolive.application.domain.HabitantRepository;
import com.simba.happycitytolive.application.usecases.dto.HabitantEligible;
import com.simba.happycitytolive.application.usecases.dto.NouvelHabitant;
import com.simba.happycitytolive.infrastructure.persistence.inmemory.InMemoryHabitantRepository;
import org.junit.jupiter.api.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by podisto on 15/01/2022.
 */
class HabitantServiceTest {

    private final HabitantRepository habitantRepository = new InMemoryHabitantRepository();
    private final Clock clock = initClock();
    private final HabitantService habitantService = new HabitantServiceImpl(habitantRepository, clock);

    @Test
    void getEligibleHabitants_shouldReturnResidentsWithArrivalDateGreaterThanAYear() {
        List<NouvelHabitant> habitants = initHabitants();
        for (NouvelHabitant nouvelHabitant: habitants) {
            habitantService.addHabitant(nouvelHabitant);
        }

        List<HabitantEligible> habitantEligibles = habitantService.getEligibleHabitants();

        assertThat(habitantEligibles.size()).isEqualTo(2);
        assertThat(habitantEligibles.get(0).getDateNaissance()).isEqualTo("08/10/1980");
    }

    @Test
    void addHabitant() {
        NouvelHabitant nouvelHabitant = initHabitants().get(0);

        habitantService.addHabitant(nouvelHabitant);

        assertThat(habitantRepository.byEmail("marie.carin@example.fr")).isNotEmpty();
        assertThat(habitantRepository.all().size()).isEqualTo(1);
    }

    private Clock initClock() {
        LocalDateTime currentDate = LocalDateTime.of(2021, 1, 31, 0, 0);
        Instant instant = ZonedDateTime.of(currentDate, ZoneId.systemDefault()).toInstant();
        return Clock.fixed(instant, ZoneId.systemDefault());
    }

    List<NouvelHabitant> initHabitants() {
        List<NouvelHabitant> habitants = new ArrayList<>();
        NouvelHabitant habitant1 = NouvelHabitant.builder()
                .nom("Carin")
                .prenom("Marie")
                .email("marie.carin@example.fr")
                .dateNaissance("08/10/1980")
                .dateArriveeCommune("01/12/2016")
                .adresse("12 rue des Lilas")
                .build();

        NouvelHabitant habitant2 = NouvelHabitant.builder()
                .nom("Robin")
                .prenom("Patrick")
                .email("patrick.robin@example.fr")
                .dateNaissance("12/06/2000")
                .dateArriveeCommune(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .adresse("28 rue des Pivoines")
                .build();

        NouvelHabitant habitant3 = NouvelHabitant.builder()
                .nom("Moulin")
                .prenom("Camille")
                .email("camille.moulin@example.fr")
                .dateNaissance("05/02/2018")
                .dateArriveeCommune("31/01/2020")
                .adresse("1 rue des Acacias")
                .build();

        habitants.add(habitant1);
        habitants.add(habitant2);
        habitants.add(habitant3);

        return habitants;
    }

}