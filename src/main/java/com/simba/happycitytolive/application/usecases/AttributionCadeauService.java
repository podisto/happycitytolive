package com.simba.happycitytolive.application.usecases;

import java.time.LocalDate;

/**
 * Created by podisto on 16/01/2022.
 */
public interface AttributionCadeauService {

    void attribuerCadeaux(LocalDate dateArriveeCommune);
}
