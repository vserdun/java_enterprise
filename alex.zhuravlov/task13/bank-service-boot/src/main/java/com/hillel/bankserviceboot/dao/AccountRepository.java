package com.hillel.bankserviceboot.dao;

import com.hillel.bankserviceboot.model.AccountEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends CrudRepository<AccountEntity, Integer> {
    @Override
    <S extends AccountEntity> S save(S entity);

    @Override
    Optional<AccountEntity> findById(Integer integer);

    @Override
    List<AccountEntity> findAll();

    @Override
    void deleteById(Integer integer);
}
