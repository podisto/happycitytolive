package com.simba.happycitytolive.infrastructure.persistence.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by podisto on 20/01/2022.
 */
@Entity
@Table(name = "cadeaux")
@Data
public class CadeauJpa {
    @Id
    private String reference;
    private String description;
    private double montant;
    @Column(name = "min_age")
    private int minAge;
    @Column(name = "max_age")
    private int maxAge;
}
