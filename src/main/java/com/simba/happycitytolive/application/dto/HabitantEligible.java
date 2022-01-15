package com.simba.happycitytolive.application.dto;

import com.simba.happycitytolive.domain.Habitant;
import lombok.Data;

import java.time.LocalDate;

/**
 * Created by podisto on 15/01/2022.
 */
@Data
public class HabitantEligible {
    private String nom;
    private String prenom;
    private String email;
    private LocalDate dateArriveeCommune;
    private String adresse;

    public HabitantEligible(Habitant habitant) {
        this.nom = habitant.getNom();
        this.prenom = habitant.getPrenom();
        this.email = habitant.getEmail();
        this.dateArriveeCommune = habitant.getDateArriveeCommune();
        this.adresse = habitant.getAdresse();
    }
}
