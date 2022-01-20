package com.simba.happycitytolive.infrastructure.persistence.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Created by podisto on 20/01/2022.
 */
@Entity
@Table(name = "cadeaux_habitants")
@Data
public class CadeauHabitantJpa {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String reference;
    private String description;
    private double montant;
    private LocalDate dateAttribution;
}
