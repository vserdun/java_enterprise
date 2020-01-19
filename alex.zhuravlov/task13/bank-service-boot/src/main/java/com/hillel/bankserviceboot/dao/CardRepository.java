package com.hillel.bankserviceboot.dao;

import com.hillel.bankserviceboot.model.BankCard;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CardRepository extends CrudRepository<BankCard, Integer> {
    @Override
    <S extends BankCard> S save(S entity);

    @Override
    Optional<BankCard> findById(Integer integer);

    @Override
    List<BankCard> findAll();

    @Override
    void deleteById(Integer integer);
}
