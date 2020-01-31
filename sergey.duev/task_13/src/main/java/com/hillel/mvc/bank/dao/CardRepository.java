package com.hillel.mvc.bank.dao;

import com.hillel.mvc.bank.models.entities.CardEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CardRepository extends CrudRepository<CardEntity, Long> {

    @Override
    List<CardEntity> findAll();

    @Override
    Optional<CardEntity> findById(Long aLong);

    @Override
    <S extends CardEntity> S save(S entity);

    @Override
    void deleteById(Long aLong);
}
