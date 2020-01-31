package com.hillel.mvc.bank.dao;

import com.hillel.mvc.bank.models.entities.BankAccountEntity;
import com.hillel.mvc.bank.models.entities.CardEntity;
import org.hibernate.Session;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BankAccountsRepository extends CrudRepository<BankAccountEntity, Long> {

    @Override
    <S extends BankAccountEntity> S save(S entity);

    @Query("from BankAccountEntity b where b.userEntity.id = :userId and b.id = :bankAccountId")
    BankAccountEntity getUserBankAccount(@Param("userId") long userId, @Param("bankAccountId") long bankAccountId);

    @Override
    Optional<BankAccountEntity> findById(Long aLong);
}
