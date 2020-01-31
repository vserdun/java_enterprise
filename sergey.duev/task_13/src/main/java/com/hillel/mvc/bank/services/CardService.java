package com.hillel.mvc.bank.services;

import com.hillel.mvc.bank.dao.CardRepository;
import com.hillel.mvc.bank.models.dto.CardCreateDTO;
import com.hillel.mvc.bank.models.dto.CardDTO;
import com.hillel.mvc.bank.models.entities.CardEntity;
import com.hillel.mvc.bank.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CardService {

    private CardRepository cardRepository;
    private Mapper mapper;

    @Autowired
    public CardService(CardRepository cardRepository, Mapper mapper) {
        this.cardRepository = cardRepository;
        this.mapper = mapper;
    }

    @Transactional
    public CardDTO addCard(CardCreateDTO dto) {
        CardEntity cardEntity = mapper.map(dto, CardEntity.class);
        cardEntity = cardRepository.save(cardEntity);
        return mapper.map(cardEntity, CardDTO.class);
    }

    @Transactional
    public List<CardDTO> getAllCards() {
        return mapper.mapList(cardRepository.findAll(), CardDTO.class);
    }

    @Transactional
    public CardDTO getCard(long id) {
        return mapper.map(cardRepository.findById(id).get(), CardDTO.class);
    }

    @Transactional
    public void deleteCard(long id) {
        cardRepository.deleteById(id);
    }
}
