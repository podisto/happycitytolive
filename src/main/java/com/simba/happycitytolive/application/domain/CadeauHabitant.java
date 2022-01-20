package com.simba.happycitytolive.application.domain;

import lombok.AccessLevel;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Created by podisto on 16/01/2022.
 */
@Getter
public class CadeauHabitant {
    private final String nom;
    private final String prenom;
    private final String email;
    private final String reference;
    @Getter(AccessLevel.NONE)
    private final String description;
    @Getter(AccessLevel.NONE)
    private final BigDecimal montant;
    private final LocalDate dateAttribution = LocalDate.now();

    public CadeauHabitant(String nom, String prenom, String email, String reference, String description, BigDecimal montant) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.reference = reference;
        this.description = description;
        this.montant = montant;
    }

    public String getDetails() {
        return this.description + " " +
                "(Montant : " + this.montant + "€ - " +
                "Référence : " + this.reference + ")";
    }
}
