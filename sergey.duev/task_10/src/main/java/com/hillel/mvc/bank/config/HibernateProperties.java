package com.hillel.mvc.bank.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "hibernate")
@Getter
@Setter
public class HibernateProperties {
    private String dialect;
    private String show_sql;
    private String hbm2ddl_auto;
}
