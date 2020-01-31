package com.hillel.mvc.bank.dao.hibernate;

import com.hillel.mvc.bank.dao.BankAccountsRepository;
import com.hillel.mvc.bank.models.entities.BankAccountEntity;
import com.hillel.mvc.bank.models.entities.CardEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public class BankAccountsRepositoryImpl implements BankAccountsRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addUserBankAccount(BankAccountEntity bankAccount) {
        Session session = sessionFactory.getCurrentSession();
        session.save(bankAccount);
    }

    @Override
    public void updateUserBankAccount(BankAccountEntity bankAccount) {
        Session session = sessionFactory.getCurrentSession();
        BankAccountEntity bankAccountEntity = session.get(BankAccountEntity.class, bankAccount.getId());
        bankAccount.setUserEntity(bankAccountEntity.getUserEntity());
        session.merge(bankAccount);
    }

    @Override
    public void deleteUserBankAccount(long userId, long bankAccountId) {
        Session session = sessionFactory.getCurrentSession();
        session.createQuery("delete BankAccountEntity e where e.userEntity.id = :userId and e.id = :bankAccountId")
            .setParameter("userId", userId)
            .setParameter("bankAccountId", bankAccountId)
            .executeUpdate();
    }

    @Override
    public BankAccountEntity getUserBankAccount(long userId, long bankAccountId) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from BankAccountEntity b where b.userEntity.id = :userId and b.id = :bankAccountId", BankAccountEntity.class)
                .setParameter("userId", userId)
                .setParameter("bankAccountId", bankAccountId)
                .getSingleResult();
    }

    @Override
    public BankAccountEntity getBankAccount(long bankAccountId) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(BankAccountEntity.class, bankAccountId);
    }
}
