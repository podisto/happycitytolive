package com.simba.happycitytolive.application.domain;

import lombok.Data;

import java.time.LocalDate;
import java.time.Period;

/**
 * Created by podisto on 15/01/2022.
 */
@Data
public class Habitant {
    private String id;
    private final String nom;
    private final String prenom;
    private final String email;
    private final LocalDate dateNaissance;
    private final LocalDate dateArriveeCommune;
    private final String adresse;
    private boolean cadeauOffert;

    private Habitant() {
        this(null, null, null, null, null, null);
    }

    public boolean hasNoCadeauOffert() {
        return !cadeauOffert;
    }

    public Habitant(String nom, String prenom, String email, String dateNaissance, String dateArrivee, String adresse) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.dateNaissance = DateFormatter.toDate(dateNaissance);
        this.dateArriveeCommune = DateFormatter.toDate(dateArrivee);
        this.adresse = adresse;
    }

    public boolean isDateArriveeCommuneGreaterThanAYear(LocalDate currentDate, int anneeEligibilite) {
        return Period.between(this.dateArriveeCommune, currentDate).getYears() >= anneeEligibilite;
    }

    public int getAge() {
        LocalDate today = LocalDate.now();
        return Period.between(this.dateNaissance, today).getYears();
    }
}
