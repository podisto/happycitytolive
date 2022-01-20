package com.simba.happycitytolive.infrastructure.persistence.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by podisto on 20/01/2022.
 */
@Entity
@Table(name = "cadeaux_habitants")
@Data
public class CadeauHabitantJpa {
    @Id
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String reference;
    private String description;
    private double montant;
}
