package com.hillel.mvc.bank.dao.hibernate;

import com.hillel.mvc.bank.dao.CardRepository;
import com.hillel.mvc.bank.models.entities.CardEntity;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CardRepositoryImpl implements CardRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<CardEntity> getAllCards() {
        return sessionFactory.getCurrentSession().createQuery("from CardEntity", CardEntity.class).getResultList();
    }

    @Override
    public CardEntity getCard(long id) {
        return sessionFactory.getCurrentSession().get(CardEntity.class, id);
    }

    @Override
    public long createCard(CardEntity cardEntity) {
        return (Long) sessionFactory.getCurrentSession().save(cardEntity);
    }

    @Override
    public void deleteCard(long id) {
        sessionFactory.getCurrentSession().createQuery("delete CardEntity where id = :id").setParameter("id", id).executeUpdate();
    }
}
