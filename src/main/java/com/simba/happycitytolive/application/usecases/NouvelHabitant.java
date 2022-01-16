package com.simba.happycitytolive.application.usecases;

import lombok.Builder;
import lombok.Data;

/**
 * Created by podisto on 16/01/2022.
 */
@Data
@Builder
public class NouvelHabitant {
    private String nom;
    private String prenom;
    private String email;
    private String dateNaissance;
    private String dateArriveeCommune;
    private String adresse;
}
