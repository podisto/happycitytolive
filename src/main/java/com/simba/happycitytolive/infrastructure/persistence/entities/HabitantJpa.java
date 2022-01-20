package com.simba.happycitytolive.infrastructure.persistence.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

/**
 * Created by podisto on 20/01/2022.
 */
@Entity
@Table(name = "habitants")
@Data
public class HabitantJpa {
    @Id
    private String id;
    private String nom;
    private String prenom;
    private String email;
    @Column(name = "date_naissance")
    private LocalDate dateNaissance;
    @Column(name = "date_arrivee_commune")
    private LocalDate dateArriveeCommune;
    private String adresse;
    @Column(name = "cadeau_offert")
    private boolean cadeauOffert;
}
