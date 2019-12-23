package com.hillel.bankserviceboot.dao;

import com.hillel.bankserviceboot.model.BankCard;

import java.util.List;

public interface CardRepository {
    List<BankCard> getCardsList();

    BankCard getCardById(int id);

    void save(BankCard bankCard);

    void delete(int id);
}
