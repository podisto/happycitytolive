package com.simba.happycitytolive.infrastructure.jobs;

import com.simba.happycitytolive.application.usecases.MailerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * Created by podisto on 19/01/2022.
 */
@Configuration
@EnableScheduling
@RequiredArgsConstructor
@Slf4j
public class MailerJob {

    @Autowired
    private MailerService mailService;

    @Scheduled(fixedDelay = 60000)
    public void sendReport() {
        log.info("envoi mail récapitulatif...");
        mailService.sendMailReport();
    }

}
