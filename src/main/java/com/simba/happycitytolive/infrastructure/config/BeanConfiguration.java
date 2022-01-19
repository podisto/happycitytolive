package com.simba.happycitytolive.infrastructure.config;

import com.simba.happycitytolive.application.domain.AttributionCadeauRepository;
import com.simba.happycitytolive.application.domain.CadeauRepository;
import com.simba.happycitytolive.application.domain.HabitantRepository;
import com.simba.happycitytolive.application.domain.NotificationService;
import com.simba.happycitytolive.application.usecases.AttributionCadeauService;
import com.simba.happycitytolive.application.usecases.AttributionCadeauxServiceImpl;
import com.simba.happycitytolive.application.usecases.HabitantService;
import com.simba.happycitytolive.application.usecases.HabitantServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.*;

/**
 * Created by podisto on 19/01/2022.
 */
@Configuration
public class BeanConfiguration {

    @Bean
    public Clock now() {
        LocalDateTime currentDate = LocalDateTime.now();
        Instant instant = ZonedDateTime.of(currentDate, ZoneId.systemDefault()).toInstant();
        return Clock.fixed(instant, ZoneId.systemDefault());
    }

    @Bean
    public HabitantService habitantService(HabitantRepository habitantRepository, Clock clock) {
        return new HabitantServiceImpl(habitantRepository, clock);
    }

    @Bean
    public AttributionCadeauService attributionCadeauService(AttributionCadeauRepository attributionCadeauRepository, CadeauRepository cadeauRepository, HabitantRepository habitantRepository,
                                                             NotificationService notificationService, Clock clock) {
        return new AttributionCadeauxServiceImpl(attributionCadeauRepository, cadeauRepository, habitantRepository, notificationService, clock);
    }
}
