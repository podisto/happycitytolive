package com.simba.happycitytolive.application.usecases.dto;

import com.simba.happycitytolive.application.domain.Habitant;
import lombok.Data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by podisto on 15/01/2022.
 */
@Data
public class HabitantEligible {
    private String nom;
    private String prenom;
    private String email;
    private String dateNaissance;
    private String dateArriveeCommune;
    private String adresse;

    public HabitantEligible(Habitant habitant) {
        this.nom = habitant.getNom();
        this.prenom = habitant.getPrenom();
        this.email = habitant.getEmail();
        this.dateNaissance = habitant.getDateNaissance().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        this.dateArriveeCommune = habitant.getDateArriveeCommune().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        this.adresse = habitant.getAdresse();
    }
}
