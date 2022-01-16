package com.simba.happycitytolive.application.usecases;

import com.simba.happycitytolive.application.domain.CadeauRepository;
import com.simba.happycitytolive.application.domain.InMemoryCadeauRepository;
import com.simba.happycitytolive.application.usecases.dto.HabitantEligible;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by podisto on 16/01/2022.
 */
class AttributionCadeauServiceTest {

    private final AttributionCadeauService attributionCadeauService = new AttributionCadeauxServiceImpl();
    private CadeauRepository cadeauRepository = new InMemoryCadeauRepository();

    @Test
    void testAttributionCadeaux() {
        List<HabitantEligible> habitants = initHabitants();

        attributionCadeauService.attribuerCadeaux(habitants);

        assertThat(cadeauRepository.byEmail("marie.carin@example.fr")).isNotEmpty();
        assertThat(cadeauRepository.byTrancheAge(40, 50)).containsOnlyOnce(cadeauRepository.byEmail("marie.carin@example.fr").get());
    }

    private List<HabitantEligible> initHabitants() {
        List<HabitantEligible> habitants = new ArrayList<>();
        HabitantEligible habitant1 = new HabitantEligible();
        habitant1.setNom("Carin");
        habitant1.setPrenom("Marie");
        habitant1.setEmail("marie.carin@example.fr");
        habitant1.setDateNaissance("08/10/1980");
        habitant1.setDateArriveeCommune("01/12/2016");
        habitant1.setAdresse("12 rue des Lilas");

        HabitantEligible habitant2 = new HabitantEligible();
        habitant2.setNom("Moulin");
        habitant2.setPrenom("Camille");
        habitant2.setEmail("camille.moulin@example.fr");
        habitant2.setDateNaissance("05/02/2018");
        habitant2.setDateArriveeCommune(LocalDate.now().minusYears(1).format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        habitant2.setAdresse("1 rue des Acacias");
        
        habitants.add(habitant1);
        habitants.add(habitant2);

        return habitants;
    }
}