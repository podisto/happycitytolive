package com.simba.happycitytolive.application.usecases;

import com.simba.happycitytolive.application.domain.*;
import com.simba.happycitytolive.infrastructure.inmemory.InMemoryAttributionCadeauRepository;
import com.simba.happycitytolive.application.usecases.dto.HabitantEligible;
import com.simba.happycitytolive.infrastructure.inmemory.InMemoryCadeauRepository;
import com.simba.happycitytolive.infrastructure.inmemory.InMemoryHabitantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by podisto on 16/01/2022.
 */
class AttributionCadeauServiceTest {

    private final AttributionCadeauRepository attributionCadeauRepository = new InMemoryAttributionCadeauRepository();
    private final CadeauRepository cadeauRepository = new InMemoryCadeauRepository();
    private final HabitantRepository habitantRepository = new InMemoryHabitantRepository();
    private final AttributionCadeauService attributionCadeauService = new AttributionCadeauxServiceImpl(attributionCadeauRepository, cadeauRepository, habitantRepository);

    @BeforeEach
    void setUp() {
        habitantRepository.save(new Habitant("Carin", "Marie", "marie.carin@example.fr", "08/10/1980", "01/12/2016", "12 rue des Lilas"));
        habitantRepository.save(new Habitant("Moulin", "Camille", "camille.moulin@example.fr", "05/02/2018", LocalDate.now().minusYears(1).format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), "1 rue des Acacias"));

        cadeauRepository.save(new Cadeau("70d73d02", "Peluche oggy et les cafards", 11.99, 0, 3));
        cadeauRepository.save(new Cadeau("c01c31a3", "Cheval floppy", 14.99, 0, 3));
        cadeauRepository.save(new Cadeau("fc02d2df", "Freddie la luciole", 15.99, 0, 3));
        cadeauRepository.save(new Cadeau("b3f83de3", "Pistolet à bulles à led", 8.79, 3, 6));
        cadeauRepository.save(new Cadeau("6a52d970", "Mémo les petits des animaux", 6.99, 3, 6));
        cadeauRepository.save(new Cadeau("6a64d9e7", "Le mug qui touille tout seul", 19.90, 30, 40));
        cadeauRepository.save(new Cadeau("dd1954e8", "Carte cadeau culture à Paris", 25.00, 40, 50));
        cadeauRepository.save(new Cadeau("40a88a96", "Bocal distributeur de boisson", 11.90, 40, 50));
        cadeauRepository.save(new Cadeau("6082f1f6", "Poster 100 films à voir avant de mourir", 28.90, 50, 60));
        cadeauRepository.save(new Cadeau("e72cfae4", "Coussin chauffant", 22.90, 50, 60));
        cadeauRepository.save(new Cadeau("b9dcca0d", "Chocolats Toblerone", 12.90, 60, 150));
        cadeauRepository.save(new Cadeau("90a2efeb", "Tasse photo", 13.90, 60, 150));

    }

    @Test
    void attribuerCadeaux_shouldAttributeCadeauxByTrancheAge() {
        List<HabitantEligible> habitantEligibles = initHabitants();

        attributionCadeauService.attribuerCadeaux(habitantEligibles);

        assertThat(attributionCadeauRepository.all().size()).isEqualTo(2);
        assertThat(attributionCadeauRepository.byHabitant("marie.carin@example.fr")).isNotEmpty();
        assertThat(attributionCadeauRepository.byHabitant("camille.moulin@example.fr")).isNotEmpty();
        assertThat(attributionCadeauRepository.byHabitant("marie.carin@example.fr").get().getCadeau().getTrancheAge()).isEqualTo(new TrancheAge(40, 50));
        assertThat(attributionCadeauRepository.byHabitant("camille.moulin@example.fr").get().getCadeau().getTrancheAge()).isEqualTo(new TrancheAge(0, 3));
        assertThat(attributionCadeauRepository.all()).containsOnlyOnce(attributionCadeauRepository.byHabitant("marie.carin@example.fr").get());
        assertThat(attributionCadeauRepository.all()).containsOnlyOnce(attributionCadeauRepository.byHabitant("camille.moulin@example.fr").get());
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