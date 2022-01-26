package com.simba.happycitytolive.infrastructure.config;

import com.simba.happycitytolive.application.domain.CadeauHabitantRepository;
import com.simba.happycitytolive.application.domain.CadeauRepository;
import com.simba.happycitytolive.application.domain.HabitantRepository;
import com.simba.happycitytolive.infrastructure.persistence.adapters.CadeauFileSystemAdapter;
import com.simba.happycitytolive.infrastructure.persistence.adapters.CadeauHabitantJpaAdapter;
import com.simba.happycitytolive.infrastructure.persistence.adapters.HabitantJpaAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by podisto on 19/01/2022.
 */
@Configuration
public class RepositoriesConfiguration {

    @Bean
    public CadeauRepository cadeauRepository() {
        return new CadeauFileSystemAdapter();
    }

    @Bean
    public CadeauHabitantRepository cadeauHabitantRepository() {
        return new CadeauHabitantJpaAdapter();
    }

    @Bean
    public HabitantRepository habitantRepository() {
        return new HabitantJpaAdapter();
    }
}
