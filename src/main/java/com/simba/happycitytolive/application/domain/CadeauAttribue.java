package com.simba.happycitytolive.application.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

/**
 * Created by podisto on 16/01/2022.
 */
@Getter
@RequiredArgsConstructor
public class CadeauAttribue {
    private final Habitant habitant;
    private final Cadeau cadeau;
    private final LocalDate dateAttribution = LocalDate.now();

    public String getEmail() {
        return this.habitant.getEmail();
    }

    public String getDetails() {
        return this.cadeau.getDescription() + " " +
                "(Montant : " + this.cadeau.getMontant() + "€ - " +
                "Référence : " + this.cadeau.getReference() + ")";
    }
}
