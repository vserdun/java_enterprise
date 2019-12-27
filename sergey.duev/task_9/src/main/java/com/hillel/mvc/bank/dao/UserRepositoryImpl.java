package com.hillel.mvc.bank.dao;

import com.hillel.mvc.bank.models.UserEntity;
import com.hillel.mvc.bank.models.exceptions.UserNotFoundException;
import org.hibernate.ObjectNotFoundException;
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
    public void updateUser(long id, UserEntity user) throws UserNotFoundException {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.update(user);
        } catch (ObjectNotFoundException onfe) {
            throw new UserNotFoundException(id, onfe);
        }
    }

    @Override
    public void deleteUser(long id) throws UserNotFoundException {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.createQuery("delete UserEntity where id = :id").setParameter("id", id).executeUpdate();
        } catch (ObjectNotFoundException onfe) {
            throw new UserNotFoundException(id, onfe);
        }

    }

    @Override
    public UserEntity getUser(long id) throws UserNotFoundException {
        Session session = sessionFactory.getCurrentSession();
        UserEntity userEntity = session.get(UserEntity.class, id);
        if (userEntity != null ) {
            return userEntity;
        } else {
            throw new UserNotFoundException(id);
        }
    }

    @Override
    public List<UserEntity> getAllUsers() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from UserEntity", UserEntity.class).list();
    }
}
