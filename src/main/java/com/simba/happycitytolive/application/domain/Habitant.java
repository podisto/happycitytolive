package com.simba.happycitytolive.application.domain;

import lombok.Data;

import java.time.LocalDate;
import java.time.Period;
import java.util.UUID;

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

    public Habitant(String nom, String prenom, String email, LocalDate dateNaissance, LocalDate dateArrivee, String adresse) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.dateNaissance = dateNaissance;
        this.dateArriveeCommune = dateArrivee;
        this.adresse = adresse;
    }

    public boolean isDateArriveeCommuneGreaterThanAYear(LocalDate currentDate, int year) {
        return Period.between(dateArriveeCommune, currentDate).getYears() >= year && hasNoCadeauOffert();
    }

    public boolean hasNoCadeauOffert() {
        return !cadeauOffert;
    }

    public boolean ageBetween(TrancheAge trancheAge) {
        int age = getAge();
        return age >= trancheAge.getMin() && age <= trancheAge.getMax();
    }

    public int getAge() {
        LocalDate now = LocalDate.now();
        return Period.between(this.dateNaissance, now).getYears();
    }

    public CadeauHabitant attribuerCadeau(Cadeau cadeau) {
        CadeauHabitant cadeauHabitant = new CadeauHabitant(this.getNom(), this.getPrenom(), this.getEmail(), cadeau.getReference(), cadeau.getDescription(), cadeau.getMontant());
        this.cadeauOffert = true;
        return cadeauHabitant;
    }
}
