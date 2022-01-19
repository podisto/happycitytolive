package com.simba.happycitytolive.application.domain;

import lombok.Getter;

/**
 * Created by podisto on 19/01/2022.
 */
@Getter
public class NotificationCadeau {
    private final String nom;
    private final String prenom;
    private final String email;
    private final String cadeau;

    public NotificationCadeau(Habitant habitant, String cadeau) {
        this.nom = habitant.getNom();
        this.prenom = habitant.getPrenom();
        this.email = habitant.getEmail();
        this.cadeau = cadeau;
    }
}
