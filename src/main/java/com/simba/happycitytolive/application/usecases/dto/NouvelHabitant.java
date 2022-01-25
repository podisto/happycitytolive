package com.simba.happycitytolive.application.usecases.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.OptBoolean;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Created by podisto on 16/01/2022.
 */
@Data
@Builder
public class NouvelHabitant {

    @NotBlank(message = "Le nom est obligatoire")
    @Size(min = 2, max = 25, message = "Nom invalide")
    private String nom;

    @NotBlank(message = "Le prénom est obligatoire")
    @Size(min = 2, max = 50, message = "Prénom invalide")
    private String prenom;

    @NotBlank(message = "L'email est obligatoire")
    @Email(message = "Email invalide")
    private String email;

    @NotBlank
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", lenient = OptBoolean.FALSE)
    private String dateNaissance;

    @NotBlank
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", lenient = OptBoolean.FALSE)
    private String dateArriveeCommune;

    @NotBlank(message = "L'adresse est obligatoire")
    private String adresse;
}
