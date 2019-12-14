package com.hillel.bankserviceboot.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "hibernate")
@Getter
@Setter
public class HibernateProperties {
}
