package com.simba.happycitytolive.domain;

import lombok.Data;

import java.time.LocalDate;
import java.time.Period;

/**
 * Created by podisto on 15/01/2022.
 */
@Data
public class Habitant {
    private String id;
    private String nom;
    private String prenom;
    private String email;
    private LocalDate dateNaissance;
    private LocalDate dateArriveeCommune;
    private String adresse;
    private LocalDate dateAttributionCadeau;
    private boolean cadeauOffert;

    public boolean isDateArriveeCommuneGreaterThanAYear(LocalDate currentDate) {
        return Period.between(this.dateArriveeCommune, currentDate).getYears() >= 1;
    }
}
