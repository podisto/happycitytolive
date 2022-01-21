package com.simba.happycitytolive.application.usecases.dto;

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
}
