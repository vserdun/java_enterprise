package com.hillel.mvc;

import org.modelmapper.AbstractConverter;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.TimeZone;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
@EnableAspectJAutoProxy
public class BankApplication {

    public static void main(String[] args) {
		SpringApplication.run(BankApplication.class, args);
	}

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE)
                .setFieldMatchingEnabled(true)
                .setSkipNullEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);
        mapper.addConverter( new AbstractConverter<Long, LocalDateTime>() {
            @Override
            protected LocalDateTime convert(Long source) {
                return LocalDateTime.ofInstant(
                        Instant.ofEpochMilli(source),
                        ZoneId.systemDefault());
            }
        });
        mapper.addConverter( new AbstractConverter<LocalDateTime, Long>() {
            @Override
            protected Long convert(LocalDateTime source) {
                return source.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
            }
        });
        return mapper;
    }
}
