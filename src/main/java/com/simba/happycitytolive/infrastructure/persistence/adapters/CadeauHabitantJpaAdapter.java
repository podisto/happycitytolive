package com.simba.happycitytolive.infrastructure.persistence.adapters;

import com.simba.happycitytolive.application.domain.CadeauHabitant;
import com.simba.happycitytolive.application.domain.CadeauHabitantRepository;
import com.simba.happycitytolive.infrastructure.persistence.entities.CadeauHabitantJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by podisto on 20/01/2022.
 */
@Repository
public class CadeauHabitantJpaAdapter implements CadeauHabitantRepository {

    @Autowired
    private CadeauHabitantJpaRepository cadeauHabitantJpaRepository;

    @Override
    public Optional<CadeauHabitant> byHabitant(String email) {
        Optional<CadeauHabitantJpa> optional = cadeauHabitantJpaRepository.findOptionalByEmail(email);
        if (!optional.isPresent()) {
            return Optional.empty();
        }
        CadeauHabitantJpa cadeauHabitantJpa = optional.get();
        return Optional.of(toCadeauHabitant(cadeauHabitantJpa));
    }

    @Override
    public void save(CadeauHabitant cadeauOffert) {
        CadeauHabitantJpa cadeauJpa = new CadeauHabitantJpa();
        cadeauJpa.setNom(cadeauOffert.getNom());
        cadeauJpa.setPrenom(cadeauOffert.getPrenom());
        cadeauJpa.setEmail(cadeauOffert.getEmail());
        cadeauJpa.setReference(cadeauOffert.getReference());
        cadeauJpa.setDescription(cadeauOffert.getDescription());
        cadeauJpa.setMontant(cadeauOffert.getMontant().doubleValue());
        cadeauJpa.setDateAttribution(cadeauOffert.getDateAttribution());
        cadeauHabitantJpaRepository.save(cadeauJpa);
    }

    @Override
    public List<CadeauHabitant> all() {
        return cadeauHabitantJpaRepository.findAll().stream().map(this::toCadeauHabitant).collect(Collectors.toList());
    }

    @Override
    public List<CadeauHabitant> allDistributedGiftsByDay(LocalDate dateAttribution) {
        return cadeauHabitantJpaRepository.findByDateAttribution(dateAttribution).stream().map(this::toCadeauHabitant).collect(Collectors.toList());
    }

    private CadeauHabitant toCadeauHabitant(CadeauHabitantJpa cadeauHabitantJpa) {
        return new CadeauHabitant(cadeauHabitantJpa.getNom(), cadeauHabitantJpa.getPrenom(), cadeauHabitantJpa.getEmail(),
                cadeauHabitantJpa.getReference(), cadeauHabitantJpa.getDescription(), BigDecimal.valueOf(cadeauHabitantJpa.getMontant()));
    }
}
