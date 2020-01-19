package com.hillel.bankserviceboot.dao;

import com.hillel.bankserviceboot.model.BankCard;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public class CardRepositoryImpl implements CardRepository {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }


    @Override
    public List<BankCard> getCardsList() {
        return getSession().createQuery("from BankCard", BankCard.class).list();
    }

    @Override
    public BankCard getCardById(int id) {
        return getSession().get(BankCard.class, id);
    }

    @Override
    public void save(BankCard bankCard) {
        getSession().saveOrUpdate(bankCard);
    }

    @Override
    public void delete(int id) {
        BankCard cardById = getCardById(id);
        getSession().delete(cardById);
    }


}
