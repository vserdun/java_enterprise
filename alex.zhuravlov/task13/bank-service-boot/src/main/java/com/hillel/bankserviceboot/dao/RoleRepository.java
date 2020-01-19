package com.hillel.bankserviceboot.dao;


import com.hillel.bankserviceboot.model.RoleEntity;

import java.util.List;

public interface RoleRepository {
    List<RoleEntity> getRolesList();

    void save(RoleEntity roleEntity);

    RoleEntity findByName(String roleName);

    RoleEntity createRoleIfNotFound(String name);
}
