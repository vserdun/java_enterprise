package com.hillel.bankserviceboot.dao;


import com.hillel.bankserviceboot.model.UserEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }


    @Override
    public List<UserEntity> getUsersList() {
        return getSession().createQuery("from UserEntity", UserEntity.class).list();
    }

    @Override
    public UserEntity getUserEntityById(int id) {
        return getSession().get(UserEntity.class, id);
    }

    @Override
    public void save(UserEntity userEntity) {
        getSession().saveOrUpdate(userEntity);
    }

    @Override
    public void delete(int id) {
        UserEntity userEntity = getUserEntityById(id);
        getSession().delete(userEntity);
    }
}
