package com.hillel.task_9.repository;

import com.hillel.task_9.model.AccountEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class AccountRepositoryImpl implements AccountRepository {
    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public AccountEntity getAccountById(int id) {
        Session session = sessionFactory.getCurrentSession();

        AccountEntity accountEntity = session.get(AccountEntity.class, id);
        return accountEntity;
    }


    @Override
    public void save(AccountEntity accountEntity) {
        Session session = sessionFactory.getCurrentSession();
//        session.lock(accountEntity, LockMode.PESSIMISTIC_READ);
        session.save(accountEntity);
    }

    @Override
    public void update(AccountEntity accountEntity) {
        Session session = sessionFactory.getCurrentSession();
        if (getAccountById(accountEntity.getId()) != null) {
            session.update(accountEntity);
        }
    }


    @Override
    public void deleteAccountById(int id) {
        Session session = sessionFactory.getCurrentSession();

        Query q = session.createQuery("delete from AccountEntity where id = :id");
        q.setParameter("id", id);
        q.executeUpdate();
    }


    @Override
    public List<AccountEntity> getAccountsByClientId(int id) {
        Session session = sessionFactory.getCurrentSession();

        Query q = session.createQuery("from AccountEntity where client = :id", AccountEntity.class);
        q.setParameter("id", id);
        List<AccountEntity> clientAccounts = q.list();

        return clientAccounts;
    }
}
