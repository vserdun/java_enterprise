package com.hillel.mvc.bank.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "jdbc")
@Getter
@Setter
public class JdbcProperties {
    private String driverClassName;
    private String url;
    private String username;
    private String password;
}
