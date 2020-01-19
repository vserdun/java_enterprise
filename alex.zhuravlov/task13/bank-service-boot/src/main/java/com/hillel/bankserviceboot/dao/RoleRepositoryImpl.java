package com.hillel.bankserviceboot.dao;

import com.hillel.bankserviceboot.model.RoleEntity;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.util.List;

@Repository
@Transactional
@Slf4j
public class RoleRepositoryImpl implements RoleRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<RoleEntity> getRolesList() {
        Session session = sessionFactory.getCurrentSession();
        List<RoleEntity> roleEntities = session.createQuery("from RoleEntity", RoleEntity.class).list();
        return roleEntities;
    }

    @Override
    public void save(RoleEntity roleEntity) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(roleEntity);
    }

    @Override
    public RoleEntity findByName(String roleName) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from RoleEntity where name = :name", RoleEntity.class);
        query.setParameter("name", roleName);
        try {
            RoleEntity roleEntity = (RoleEntity) query.getSingleResult();
            return roleEntity;
        } catch (NoResultException e) {
            return null;
        }

    }

    @Transactional
    @Override
    public RoleEntity createRoleIfNotFound(String name) {
        RoleEntity roleEntity = findByName(name);
        if (roleEntity == null) {
            roleEntity = new RoleEntity(name);
            save(roleEntity);
        }
        return roleEntity;
    }
}
