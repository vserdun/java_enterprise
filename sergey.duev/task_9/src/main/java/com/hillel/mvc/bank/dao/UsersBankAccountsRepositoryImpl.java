package com.hillel.mvc.bank.dao;

import com.hillel.mvc.bank.models.entities.BankAccountEntity;
import com.hillel.mvc.bank.models.exceptions.BankAccountNotFoundException;
import com.hillel.mvc.bank.models.exceptions.UserNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;

@Repository
@Transactional
public class UsersBankAccountsRepositoryImpl implements UsersBankAccountsRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addUserBankAccount(BankAccountEntity bankAccount) {
        Session session = sessionFactory.getCurrentSession();
        session.save(bankAccount);
    }

    @Override
    public void updateUserBankAccount(BankAccountEntity bankAccount) throws UserNotFoundException, BankAccountNotFoundException {
        Session session = sessionFactory.getCurrentSession();
        BankAccountEntity bankAccountEntity = session.get(BankAccountEntity.class, bankAccount.getId());
        bankAccount.setUserEntity(bankAccountEntity.getUserEntity());
        session.merge(bankAccount);
    }

    @Override
    public void deleteUserBankAccount(long userId, long bankAccountId) throws UserNotFoundException, BankAccountNotFoundException {
        Session session = sessionFactory.getCurrentSession();
        session.createQuery("delete BankAccountEntity e where e.userEntity.id = :userId and e.id = :bankAccountId")
            .setParameter("userId", userId)
            .setParameter("bankAccountId", bankAccountId)
            .executeUpdate();
    }

    @Override
    public BankAccountEntity getUserBankAccount(long userId, long bankAccountId) throws UserNotFoundException, BankAccountNotFoundException{
        Session session = sessionFactory.getCurrentSession();
        try {
            return session.createQuery("from BankAccountEntity b where b.userEntity.id = :userId and b.id = :bankAccountId", BankAccountEntity.class)
                    .setParameter("userId", userId)
                    .setParameter("bankAccountId", bankAccountId)
                    .getSingleResult();
        } catch (NoResultException nre) {
            throw new BankAccountNotFoundException(bankAccountId);
        }
    }

    @Override
    public BankAccountEntity getBankAccount(long bankAccountId) throws BankAccountNotFoundException {
        Session session = sessionFactory.getCurrentSession();
        try {
            return session.get(BankAccountEntity.class, bankAccountId);
        } catch (NoResultException nre) {
            throw new BankAccountNotFoundException(bankAccountId);
        }
    }
}
