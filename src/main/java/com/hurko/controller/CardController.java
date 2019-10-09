package com.hurko.controller;

import com.hurko.model.dto.Card;
import com.hurko.service.CardService;
import com.hurko.transformer.CardTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(path = "/card")
public class CardController {

    @Autowired
    private CardService cardService;

    @Autowired
    private CardTransformer cardTransformer;

    @PostMapping(path = "/put")
    public void putMoney(@RequestParam int money, @RequestParam String number) {
        cardService.putMoney(money, number);
    }

    @PostMapping(path = "/get")
    public void getMoney(Principal principal, @RequestParam Integer money, @RequestParam String number) {
        cardService.getMoney(principal, money, number);
    }

    @PostMapping(path = "/transfer")
    public void transferMoney(Principal principal, @RequestParam Integer money,
                              @RequestParam String fromCardNumber, @RequestParam String toCardNumber) {
        cardService.transferMoney(principal, money, fromCardNumber, toCardNumber);
    }

    @GetMapping
    public List<Card> findAll() {
        return cardTransformer.buildCards(cardService.findAll());
    }

    @GetMapping(path = "/{id}")
    public Card findById(@PathVariable("id") long id) {
        return cardTransformer.buildCard(cardService.findById(id));
    }

    @PostMapping
    public void create(@RequestBody Card card) {
        cardService.create(cardTransformer.buildCardEntity(card));
    }

    @PutMapping(path = "/{id}")
    public void update(@PathVariable("id") long id, @RequestBody Card card) {
        cardService.update(id, cardTransformer.buildCardEntity(card));
    }

    @DeleteMapping(path = "{id}")
    public void delete(@PathVariable("id") long id) {
        cardService.delete(id);
    }
}
