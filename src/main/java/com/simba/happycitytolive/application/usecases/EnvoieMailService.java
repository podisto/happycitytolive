package com.simba.happycitytolive.application.usecases;

/**
 * Created by podisto on 19/01/2022.
 */
public interface EnvoieMailService {

    /**
     * Envoi un mail récapitulatif au service Cadeau de la mairie avec tous les cadeaux attribués de la journée
     */
    void sendMailReport();
}
