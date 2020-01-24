package com.hillel.mvc.bank.dao;

import com.hillel.mvc.bank.models.entities.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

    @Override
    <S extends UserEntity> S save(S entity);

    @Override
    Optional<UserEntity> findById(Long aLong);

    @Override
    void deleteById(Long aLong);

    @Override
    List<UserEntity> findAll();

    @Query("from UserEntity where email = :email")
    UserEntity getUserByEmail(@Param("email") String email);
}
