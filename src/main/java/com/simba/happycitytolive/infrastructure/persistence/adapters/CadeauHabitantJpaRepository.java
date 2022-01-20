package com.simba.happycitytolive.infrastructure.persistence.adapters;

import com.simba.happycitytolive.infrastructure.persistence.entities.CadeauHabitantJpa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Created by podisto on 20/01/2022.
 */
public interface CadeauHabitantJpaRepository extends JpaRepository<CadeauHabitantJpa, Long> {

    Optional<CadeauHabitantJpa> findOptionalByEmail(String email);

    List<CadeauHabitantJpa> findByDateAttribution(LocalDate dateAttribution);
}
