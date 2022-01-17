package com.simba.happycitytolive.application.domain;

import java.util.List;

/**
 * Created by podisto on 17/01/2022.
 */
public interface CadeauRepository {

    List<Cadeau> byTrancheAge(int age);

    void save(Cadeau cadeau);
}
