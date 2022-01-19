package com.simba.happycitytolive.infrastructure.config;

import com.simba.happycitytolive.application.domain.HabitantRepository;
import com.simba.happycitytolive.infrastructure.inmemory.InMemoryHabitantRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by podisto on 19/01/2022.
 */
@Configuration
public class RepositoriesConfiguration {

    @Bean
    public HabitantRepository habitantRepository() {
        return new InMemoryHabitantRepository();
    }
}