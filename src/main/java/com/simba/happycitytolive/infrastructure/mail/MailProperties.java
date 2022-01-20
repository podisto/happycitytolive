package com.simba.happycitytolive.infrastructure.mail;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by podisto on 20/01/2022.
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "mail")
public class MailProperties {
    private String from;
    private String host;
    private int port;
}
