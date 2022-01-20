package com.simba.happycitytolive.infrastructure.mail;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

/**
 * Created by podisto on 20/01/2022.
 */
@Configuration
public class MailConfig {

    @Bean
    public JavaMailSender mailSender(MailProperties properties) {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(properties.getHost());
        mailSender.setPort(properties.getPort());

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.debug", "true");
        return mailSender;
    }
}
