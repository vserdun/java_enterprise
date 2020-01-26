package com.hillel.bankserviceboot.service;

import com.hillel.bankserviceboot.dao.CardRepository;
import com.hillel.bankserviceboot.model.BankCard;
import com.hillel.bankserviceboot.model.UserEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CardServiceImplTest {

    @Mock
    private CardRepository cardRepository;

    @InjectMocks
    private CardServiceImpl cardService;


    @Test
    public void getCardsList() {
        when(cardRepository.findAll()).thenReturn(Arrays.asList(
                new BankCard(1, "Privat", new HashSet<>()),
                new BankCard(2, "Monobank", new HashSet<>())));
        List<BankCard> cards = cardService.getCardsList();
        assertNotNull("Card list must be not null", cards);
        assertEquals("Wrong cards count",2 , cards.size());

    }

    @Test
    public void getCard() {
        ArgumentCaptor<Integer> cardIdArgCaptor = ArgumentCaptor.forClass(Integer.class);
        when(cardRepository.findById(cardIdArgCaptor.capture())).thenReturn(java.util.Optional.of(new BankCard(1, "Privat", new HashSet<>())));

        BankCard card = cardService.getCard(15);
        assertNotNull("Card must be not null", card);
        assertEquals("Wrong card id", 1, card.getCardId());
        assertEquals("Wrong card name", "Privat", card.getCardName());
        assertEquals("Wrong card counts", 0, card.getAccounts().size());
    }

    @Test
    public void save() {
        BankCard card1 = new BankCard();
        card1.setCardName("Privat");

        BankCard card2 = new BankCard();
        card2.setCardName("Monobank");

        cardService.save(card1);
        cardService.save(card2);
        verify(cardRepository, times(2)).save(any(BankCard.class));

    }

    @Test
    public void delete() {
        cardService.delete(1);
        verify(cardRepository, times(1)).deleteById(1);
    }
}