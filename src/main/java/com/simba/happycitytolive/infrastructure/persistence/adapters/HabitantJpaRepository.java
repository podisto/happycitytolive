package com.simba.happycitytolive.infrastructure.persistence.adapters;

import com.simba.happycitytolive.infrastructure.persistence.entities.HabitantJpa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Created by podisto on 20/01/2022.
 */
public interface HabitantJpaRepository extends JpaRepository<HabitantJpa, String> {

    List<HabitantJpa> findByDateArriveeCommuneLessThanEqualAndCadeauOffertIsFalse(LocalDate previousYear);

    Optional<HabitantJpa> findOptionalByEmail(String email);
}
