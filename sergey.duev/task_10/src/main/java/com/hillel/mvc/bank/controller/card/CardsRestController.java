package com.hillel.mvc.bank.controller.card;

import com.hillel.mvc.bank.controller.dto.Card;
import com.hillel.mvc.bank.controller.dto.CardCreate;
import com.hillel.mvc.bank.models.dto.CardCreateDTO;
import com.hillel.mvc.bank.services.CardService;
import com.hillel.mvc.bank.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/bank")
public class CardsRestController {

    public static final String PATH_CARDS = "/cards";
    public static final String VAR_CARD_ID = "cardId";

    private CardService cardService;
    private Mapper mapper;

    @Autowired
    public CardsRestController(CardService cardService, Mapper mapper) {
        this.cardService = cardService;
        this.mapper = mapper;
    }

    @GetMapping(PATH_CARDS)
    public List<Card> getAllCards() {
        return mapper.mapList(cardService.getAllCards(), Card.class);
    }

    @GetMapping(PATH_CARDS + "/{" + VAR_CARD_ID + "}")
    public Card getCard(@PathVariable(VAR_CARD_ID) long id) {
        return mapper.map(cardService.getCard(id), Card.class);
    }

    @PutMapping(PATH_CARDS)
    public Card addCard(@Valid @RequestBody CardCreate cardCreate) {
        return mapper.map(cardService.addCard(mapper.map(cardCreate, CardCreateDTO.class)), Card.class);
    }

    @DeleteMapping(PATH_CARDS + "/{" + VAR_CARD_ID + "}")
    public void deleteCard(@PathVariable(VAR_CARD_ID) long id) {
        cardService.deleteCard(id);
    }
}
