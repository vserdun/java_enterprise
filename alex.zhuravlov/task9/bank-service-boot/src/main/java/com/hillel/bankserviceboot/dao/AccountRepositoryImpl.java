package com.hillel.bankserviceboot.dao;


import com.hillel.bankserviceboot.model.AccountEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class AccountRepositoryImpl implements AccountRepository {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Transactional
    @Override
    public List<AccountEntity> getAccountsList() {
        return sessionFactory.getCurrentSession().createQuery("from AccountEntity", AccountEntity.class).list();
    }

    @Override
    public AccountEntity getAccountEntityById(int id) {
        return sessionFactory.getCurrentSession().get(AccountEntity.class, id);
    }

    @Override
    public void save(AccountEntity accountEntity) {
        sessionFactory.getCurrentSession().saveOrUpdate(accountEntity);
    }

    @Override
    public void delete(int id) {
        AccountEntity accountEntity = getAccountEntityById(id);
        sessionFactory.getCurrentSession().delete(accountEntity);
    }
}
