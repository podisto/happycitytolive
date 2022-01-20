package com.simba.happycitytolive.application.usecases.dto;

import com.simba.happycitytolive.application.domain.DateFormatter;
import com.simba.happycitytolive.application.domain.Habitant;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by podisto on 15/01/2022.
 */
@Data
@NoArgsConstructor
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
        this.dateNaissance = DateFormatter.toString(habitant.getDateNaissance());
        this.dateArriveeCommune = DateFormatter.toString(habitant.getDateArriveeCommune());
        this.adresse = habitant.getAdresse();
    }
}
