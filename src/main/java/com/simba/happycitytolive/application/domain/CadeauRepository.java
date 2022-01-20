package com.simba.happycitytolive.application.domain;

import java.util.List;
import java.util.Optional;

/**
 * Created by podisto on 17/01/2022.
 */
public interface CadeauRepository {

    List<Cadeau> byTrancheAge(Habitant habitant);

    void save(Cadeau cadeau);

    Optional<Cadeau> byReference(String reference);
}
