package com.hillel.bankserviceboot.service;

import com.hillel.bankserviceboot.model.BankCard;

import java.util.List;

public interface CardService {
    List<BankCard> getCardsList();

    BankCard getCard(int id);

    void save(BankCard bankCard);

    void delete(int id);
}
