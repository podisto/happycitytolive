package com.simba.happycitytolive.infrastructure.persistence.adapters;

import com.simba.happycitytolive.infrastructure.persistence.entities.CadeauJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * Created by podisto on 20/01/2022.
 */
public interface CadeauJpaRepository extends JpaRepository<CadeauJpa, String> {

    @Query("select c from CadeauJpa c where c.minAge <= :age and :age <= c.maxAge")
    List<CadeauJpa> findByTrancheAge(@Param("age") int age);

    Optional<CadeauJpa> findOptionalByReference(String reference);
}
