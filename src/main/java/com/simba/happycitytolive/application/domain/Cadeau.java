package com.simba.happycitytolive.application.domain;

import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * Created by podisto on 17/01/2022.
 */
@Getter
@ToString
public class Cadeau {
    private final String reference;
    private final String description;
    private final BigDecimal montant;
    private final TrancheAge trancheAge;

    public Cadeau(String reference, String description, double montant, int min, int max) {
        this.reference = reference;
        this.description = description;
        this.montant = BigDecimal.valueOf(montant);
        this.trancheAge = new TrancheAge(min, max);
    }

}
