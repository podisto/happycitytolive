package com.simba.happycitytolive.infrastructure.persistence.adapters;

import com.simba.happycitytolive.application.domain.Habitant;
import com.simba.happycitytolive.application.domain.HabitantRepository;
import com.simba.happycitytolive.infrastructure.persistence.entities.HabitantJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by podisto on 20/01/2022.
 */
@Repository
public class HabitantJpaAdapter implements HabitantRepository {

    @Autowired
    private HabitantJpaRepository habitantJpaRepository;

    @Override
    public void save(Habitant habitant) {
        HabitantJpa habitantEntity = new HabitantJpa();
        habitantEntity.setId(UUID.randomUUID().toString());
        habitantEntity.setNom(habitant.getNom());
        habitantEntity.setPrenom(habitant.getPrenom());
        habitantEntity.setDateNaissance(habitant.getDateNaissance());
        habitantEntity.setDateArriveeCommune(habitant.getDateArriveeCommune());
        habitantEntity.setAdresse(habitant.getAdresse());
        habitantEntity.setEmail(habitant.getEmail());
        habitantEntity.setCadeauOffert(habitant.isCadeauOffert());
        habitantJpaRepository.save(habitantEntity);
    }

    @Override
    public List<Habitant> findEligibleHabitants(LocalDate currentDate) {
        List<HabitantJpa> eligibleResidents = habitantJpaRepository.findByDateArriveeCommuneLessThanEqualAndCadeauOffertIsFalse(currentDate.minusYears(1));
        return eligibleResidents.stream()
                .map(this::toHabitant)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Habitant> byEmail(String email) {
        Optional<HabitantJpa> optionalHabitant = habitantJpaRepository.findOptionalByEmail(email);
        if (!optionalHabitant.isPresent()) {
            return Optional.empty();
        }
        HabitantJpa habitant = optionalHabitant.get();
        return Optional.of(toHabitant(habitant));
    }

    private Habitant toHabitant(HabitantJpa entity) {
        return new Habitant(entity.getNom(), entity.getPrenom(), entity.getEmail(),
                entity.getDateNaissance(), entity.getDateArriveeCommune(), entity.getAdresse());
    }
}
