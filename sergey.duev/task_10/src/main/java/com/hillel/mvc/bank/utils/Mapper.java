package com.hillel.mvc.bank.utils;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class Mapper {

    private ModelMapper modelMapper;

    @Autowired
    public Mapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
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
