package com.simba.happycitytolive;

import com.simba.happycitytolive.application.dto.HabitantEligible;
import com.simba.happycitytolive.domain.Habitant;
import com.simba.happycitytolive.domain.HabitantRepository;
import com.simba.happycitytolive.application.HabitantService;
import com.simba.happycitytolive.application.HabitantServiceImpl;
import com.simba.happycitytolive.infrastructure.inmemory.InMemoryHabitantRepository;
import org.junit.jupiter.api.Test;

import java.time.*;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by podisto on 15/01/2022.
 */
class HabitantServiceTest {

    private HabitantRepository habitantRepository = new InMemoryHabitantRepository();
    private Clock clock = initFixedClock();
    private HabitantService habitantService = new HabitantServiceImpl(habitantRepository, clock);

    @Test
    void retrieveEligibleResidents_shouldReturnResidentsWithArrivalDateGreaterThanAYear() {
        List<Habitant> habitants = initHabitant();
        habitantRepository.add(habitants);

        List<HabitantEligible> habitantEligibles = habitantService.retrieveEligibleResidents();

        assertThat(habitantEligibles.size()).isEqualTo(2);
    }

    private Clock initFixedClock() {
        LocalDateTime currentDate = LocalDateTime.of(2022, 1, 15, 18, 10);
        Instant instant = ZonedDateTime.of(currentDate, ZoneId.systemDefault()).toInstant();
        return Clock.fixed(instant, ZoneId.systemDefault());
    }

    private List<Habitant> initHabitant() {
        List<Habitant> habitants = new ArrayList<>();
        Habitant habitant1 = new Habitant();
        habitant1.setId("5e18367a-1eb3-4b91-b87a-44cd210ef7ba");
        habitant1.setNom("Carin");
        habitant1.setPrenom("Marie");
        habitant1.setEmail("marie.carin@example.fr");
        habitant1.setDateNaissance(LocalDate.of(1980,10,8));
        habitant1.setDateArriveeCommune(LocalDate.of(2016, 12,1));
        habitant1.setAdresse("12 rue des Lilas");

        Habitant habitant2 = new Habitant();
        habitant2.setId("939c0a28-c407-4ce3-b661-d96a412a3d29");
        habitant2.setNom("Robin");
        habitant2.setPrenom("Patrick");
        habitant2.setEmail("patrick.robin@example.fr");
        habitant2.setDateNaissance(LocalDate.of(2000,6,12));
        habitant2.setDateArriveeCommune(LocalDate.now());
        habitant2.setAdresse("28 rue des Pivoines");

        Habitant habitant3 = new Habitant();
        habitant3.setId("aebb21fa-b981-4baa-9668-52be5ea3ce90");
        habitant3.setNom("Moulin");
        habitant3.setPrenom("Camille");
        habitant3.setEmail("camille.moulin@example.fr");
        habitant3.setDateNaissance(LocalDate.of(2018,2,5));
        habitant3.setDateArriveeCommune(LocalDate.now().minusYears(1));
        habitant3.setAdresse("1 rue des Acacias");

        habitants.add(habitant1);
        habitants.add(habitant2);
        habitants.add(habitant3);

        return habitants;
    }
}