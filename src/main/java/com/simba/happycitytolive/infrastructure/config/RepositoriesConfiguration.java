package com.simba.happycitytolive.infrastructure.config;

import com.simba.happycitytolive.application.domain.AttributionCadeauRepository;
import com.simba.happycitytolive.application.domain.CadeauRepository;
import com.simba.happycitytolive.application.domain.HabitantRepository;
import com.simba.happycitytolive.infrastructure.persistence.inmemory.InMemoryAttributionCadeauRepository;
import com.simba.happycitytolive.infrastructure.persistence.inmemory.InMemoryCadeauRepository;
import com.simba.happycitytolive.infrastructure.persistence.inmemory.InMemoryHabitantRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by podisto on 19/01/2022.
 */
@Configuration
public class RepositoriesConfiguration {

    @Bean
    public CadeauRepository cadeauRepository() {
        return new InMemoryCadeauRepository();
    }

    @Bean
    public AttributionCadeauRepository attributionCadeauRepository() {
        return new InMemoryAttributionCadeauRepository();
    }

    @Bean
    public HabitantRepository habitantRepository() {
        return new InMemoryHabitantRepository();
    }
}
