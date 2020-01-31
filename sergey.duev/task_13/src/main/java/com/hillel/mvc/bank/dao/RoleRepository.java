package com.hillel.mvc.bank.dao;

import com.hillel.mvc.bank.models.entities.RoleEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoleRepository extends CrudRepository<RoleEntity, Long> {

    @Override
    <S extends RoleEntity> S save(S entity);

    @Query("from RoleEntity where name = :name")
    RoleEntity findRoleByName(@Param("name") String name);

    @Override
    Iterable<RoleEntity> findAll();
}
