package com.simba.happycitytolive.infrastructure.persistence.adapters;

import com.simba.happycitytolive.application.domain.Cadeau;
import com.simba.happycitytolive.application.domain.CadeauRepository;
import com.simba.happycitytolive.application.domain.Habitant;
import com.simba.happycitytolive.infrastructure.persistence.entities.CadeauJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by podisto on 20/01/2022.
 */
@Repository
public class CadeauJpaAdapter implements CadeauRepository {

    @Autowired
    private CadeauJpaRepository cadeauJpaRepository;

    @Override
    public List<Cadeau> byTrancheAge(Habitant habitant) {
        List<CadeauJpa> cadeaux = cadeauJpaRepository.findByTrancheAge(habitant.getAge());
        return cadeaux.stream().map(c -> new Cadeau(c.getReference(), c.getDescription(), c.getMontant(), c.getMinAge(), c.getMaxAge())).collect(Collectors.toList());
    }

    @Override
    public void save(Cadeau cadeau) {
        CadeauJpa cadeauEntity = new CadeauJpa();
        cadeauEntity.setReference(cadeau.getReference());
        cadeauEntity.setDescription(cadeau.getDescription());
        cadeauEntity.setMontant(cadeau.getMontant().doubleValue());
        cadeauEntity.setMinAge(cadeau.getTrancheAge().getMin());
        cadeauEntity.setMaxAge(cadeau.getTrancheAge().getMax());
        cadeauJpaRepository.save(cadeauEntity);
    }

    @Override
    public Optional<Cadeau> byReference(String reference) {
        return cadeauJpaRepository.findOptionalByReference(reference).map(this::toCadeau);
    }

    private Cadeau toCadeau(CadeauJpa cadeauJpa) {
        return new Cadeau(cadeauJpa.getReference(), cadeauJpa.getDescription(), cadeauJpa.getMontant(), cadeauJpa.getMinAge(), cadeauJpa.getMaxAge());
    }
}
