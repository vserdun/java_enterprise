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

    @Override
    public List<AccountEntity> getAccountsList() {
        return getSession().createQuery("from AccountEntity", AccountEntity.class).list();
    }

    @Override
    public AccountEntity getAccountEntityById(int id) {
        return getSession().get(AccountEntity.class, id);
    }

    @Override
    public void save(AccountEntity accountEntity) {
        getSession().saveOrUpdate(accountEntity);
    }

    @Override
    public void delete(int id) {
        AccountEntity accountEntity = getAccountEntityById(id);
        getSession().delete(accountEntity);
    }

}
