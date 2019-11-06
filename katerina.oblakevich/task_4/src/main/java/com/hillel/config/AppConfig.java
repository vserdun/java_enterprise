package com.hillel.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.time.LocalDate;

@Configuration
public class AppConfig {

    @Bean("dateBean")
    @Scope
    public LocalDate getLocalDate() {
        return LocalDate.now();
    }
}
