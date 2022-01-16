package com.simba.happycitytolive.application.domain;

import java.util.List;
import java.util.Optional;

/**
 * Created by podisto on 16/01/2022.
 */
public interface CadeauRepository {

    List<Cadeau> byTrancheAge(int min, int max);

    Optional<Cadeau> byEmail(String email);
}
