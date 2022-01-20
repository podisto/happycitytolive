package com.simba.happycitytolive.application.usecases;

import com.simba.happycitytolive.application.domain.*;
import com.simba.happycitytolive.infrastructure.persistence.inmemory.InMemoryCadeauHabitantRepository;
import com.simba.happycitytolive.infrastructure.persistence.inmemory.InMemoryCadeauRepository;
import com.simba.happycitytolive.infrastructure.persistence.inmemory.InMemoryHabitantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.*;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

/**
 * Created by podisto on 19/01/2022.
 */
class MailerServiceTest {

    private final CadeauHabitantRepository cadeauHabitantRepository = new InMemoryCadeauHabitantRepository();
    private final NotificationService notificationService = mock(NotificationService.class);
    private final Clock clock = initFixedClock();
    private final CadeauRepository cadeauRepository = new InMemoryCadeauRepository();
    private final HabitantRepository habitantRepository = new InMemoryHabitantRepository();
    private final AttributionCadeauService attributionCadeauService = new AttributionCadeauxServiceImpl(cadeauHabitantRepository, cadeauRepository, habitantRepository, notificationService, clock);
    private final MailerService mailService = new MailerServiceImpl(cadeauHabitantRepository, notificationService, clock);

    @BeforeEach
    void setUp() {
        cadeauRepository.save(new Cadeau("70d73d02", "Peluche oggy et les cafards", 11.99, 0, 3));
        cadeauRepository.save(new Cadeau("c01c31a3", "Cheval floppy", 14.99, 0, 3));
        cadeauRepository.save(new Cadeau("b3f83de3", "Pistolet à bulles à led", 8.79, 3, 6));
        cadeauRepository.save(new Cadeau("6a52d970", "Mémo les petits des animaux", 6.99, 3, 6));
        cadeauRepository.save(new Cadeau("2ee03dac", "Boîte en bois mikado", 5.99, 6, 10));
        cadeauRepository.save(new Cadeau("dbe982da", "Puissance 4 voyage", 7.99, 6, 10));
        cadeauRepository.save(new Cadeau("6890e222", "Puzzle 1000 pièces magnifique ville de new-york", 14.99, 10, 15));
        cadeauRepository.save(new Cadeau("95352fa2", "Kit kart hoverboard", 39.99, 10, 15));
        cadeauRepository.save(new Cadeau("cae67bbb", "Tasse magique Licorne", 21.90, 15, 20));
        cadeauRepository.save(new Cadeau("225973d8", "Carte du monde en liège", 23.90, 15, 20));
        cadeauRepository.save(new Cadeau("07eb02d3", "Carte du monde 3D", 30.25, 20, 30));
        cadeauRepository.save(new Cadeau("3d1248c5", "Sac bandoulière", 18.65, 20, 30));
        cadeauRepository.save(new Cadeau("6a64d9e7", "Le mug qui touille tout seul", 19.90, 30, 40));
        cadeauRepository.save(new Cadeau("6a64d9e7", "Le mug qui touille tout seul", 19.90, 30, 40));
        cadeauRepository.save(new Cadeau("dd1954e8", "Carte cadeau culture à Paris", 25.00, 40, 50));
        cadeauRepository.save(new Cadeau("40a88a96", "Bocal distributeur de boisson", 11.90, 40, 50));
        cadeauRepository.save(new Cadeau("6082f1f6", "Poster 100 films à voir avant de mourir", 28.90, 50, 60));
        cadeauRepository.save(new Cadeau("e72cfae4", "Coussin chauffant", 22.90, 50, 60));
        cadeauRepository.save(new Cadeau("b9dcca0d", "Chocolats Toblerone", 12.90, 60, 150));
        cadeauRepository.save(new Cadeau("90a2efeb", "Tasse photo", 13.90, 60, 150));

        habitantRepository.save(new Habitant("Carin", "Marie", "marie.carin@example.fr", LocalDate.of(1980, 10, 8) , LocalDate.of(2016, 12, 1),"12 rue des Lilas"));
        habitantRepository.save(new Habitant("Robin", "Patrick", "patrick.robin@example.fr", LocalDate.of(2000, 6, 12), LocalDate.of(2020, 1, 1), "1 rue des Acacias"));
        habitantRepository.save(new Habitant("Moulin", "Camille", "camille.moulin@example.fr", LocalDate.of(2018, 2, 5), LocalDate.now(), "1 rue des Acacias"));
    }

    @Test
    void sendMailReport_shouldReturnMessageNoDistributedGifts() {
        mailService.sendMailReport();

        verify(notificationService, times(0)).sendMailRecapitulatif(anyList());
    }

    @Test
    void sendMailReport_shouldContainAllDistributedGiftsToday() {
        attributionCadeauService.attribuer();

        mailService.sendMailReport();

        verify(notificationService, times(1)).sendMailAnnonceAttribution(anyList());
    }

    private Clock initFixedClock() {
        LocalDateTime currentDate = LocalDateTime.now();
        Instant instant = ZonedDateTime.of(currentDate, ZoneId.systemDefault()).toInstant();
        return Clock.fixed(instant, ZoneId.systemDefault());
    }
}