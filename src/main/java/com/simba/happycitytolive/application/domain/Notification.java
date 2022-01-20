package com.simba.happycitytolive.application.domain;

import lombok.Getter;

/**
 * Created by podisto on 19/01/2022.
 */
@Getter
public class Notification {
    private final String nom;
    private final String prenom;
    private final String email;
    private final String content;

    public Notification(String nom, String prenom, String email, String content) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.content = content;
    }

    public String getMessage() {
        return prenom.concat(" ").concat(nom).concat(": ").concat(content);
    }
}
