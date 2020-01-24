package com.hillel.mvc.bank.utils;

import org.modelmapper.AbstractConverter;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class Mapper {

    private ModelMapper modelMapper;

    public Mapper() {
        this.modelMapper = modelMapper();
    }

    private ModelMapper modelMapper() {
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

    public <D, T> List<D> mapList(final Collection<T> entityList, Class<D> outCLass) {
        return entityList.stream()
                .map(entity -> map(entity, outCLass))
                .collect(Collectors.toList());
    }

    public <D, T> Set<D> mapSet(final Collection<T> entityList, Class<D> outCLass) {
        return entityList.stream()
                .map(entity -> map(entity, outCLass))
                .collect(Collectors.toSet());
    }

    public <D, T> D map(final T entity, Class<D> outClass) {
        return modelMapper.map(entity, outClass);
    }

    public <S, D> D map(final S source, D destination) {
        modelMapper.map(source, destination);
        return destination;
    }
}
