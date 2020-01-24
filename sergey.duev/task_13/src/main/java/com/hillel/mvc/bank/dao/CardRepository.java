package com.hillel.mvc.bank.dao;

import com.hillel.mvc.bank.models.entities.CardEntity;

import java.util.List;

public interface CardRepository {

    List<CardEntity> getAllCards();

    CardEntity getCard(long id);

    long createCard(CardEntity cardEntity);

    void deleteCard(long id);
}
