package com.hillel.mvc.bank.dao.hibernate;

import com.hillel.mvc.bank.dao.UserRepository;
import com.hillel.mvc.bank.models.entities.UserEntity;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public long addUser(UserEntity user) {
        Session session = sessionFactory.getCurrentSession();
        return (Long)session.save(user);
    }

    @Override
    public void updateUser(UserEntity user) {
        Session session = sessionFactory.getCurrentSession();
        session.update(user);
    }

    @Override
    public void deleteUser(long id) {
        Session session = sessionFactory.getCurrentSession();
        session.createQuery("delete UserEntity where id = :id").setParameter("id", id).executeUpdate();
    }

    @Override
    public UserEntity getUser(long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(UserEntity.class, id);
    }

    @Override
    public List<UserEntity> getAllUsers() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from UserEntity", UserEntity.class).list();
    }
}
