package com.simba.happycitytolive.infrastructure.persistence.adapters;

import com.simba.happycitytolive.application.domain.AttributionCadeauRepository;
import com.simba.happycitytolive.application.domain.CadeauHabitant;
import com.simba.happycitytolive.infrastructure.persistence.entities.CadeauHabitantJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Created by podisto on 20/01/2022.
 */
@Repository
public class AttributionCadeauJpaAdapter implements AttributionCadeauRepository {

    @Autowired
    private AttributionCadeauJpaRepository cadeauJpaRepository;

    @Override
    public Optional<CadeauHabitant> byHabitant(String email) {
        Optional<CadeauHabitantJpa> optional = cadeauJpaRepository.findOptionalByEmail(email);
        if (!optional.isPresent()) {
            return Optional.empty();
        }
        CadeauHabitantJpa cadeauHabitantJpa = optional.get();
        return Optional.of(toCadeauHabitant(cadeauHabitantJpa));
    }

    private CadeauHabitant toCadeauHabitant(CadeauHabitantJpa cadeauHabitantJpa) {
        return new CadeauHabitant(cadeauHabitantJpa.getNom(), cadeauHabitantJpa.getPrenom(), cadeauHabitantJpa.getEmail(), cadeauHabitantJpa.getReference(), cadeauHabitantJpa.getDescription(), cadeauHabitantJpa.getMontant());
    }

    @Override
    public void save(CadeauHabitant cadeauOffert) {

    }

    @Override
    public List<CadeauHabitant> all() {
        return null;
    }

    @Override
    public List<CadeauHabitant> allDistributedGiftsByDay(LocalDate dateAttribution) {
        return null;
    }
}
