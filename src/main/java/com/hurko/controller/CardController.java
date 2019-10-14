package com.hurko.controller;

import com.hurko.model.dto.Card;
import com.hurko.service.CardService;
import com.hurko.transformer.CardTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(path = "/card")
public class CardController {

    @Autowired
    private CardService cardService;

    @Autowired
    private CardTransformer cardTransformer;

    @PostMapping(path = "/replenishment")
    public void replenishmentMoney(@RequestParam int sum, @RequestParam String account) {
        cardService.replenishmentMoney(sum, account);
    }

    @PostMapping(path = "/withdraw")
    public void withdrawMoney(Principal principal, @RequestParam Integer sum, @RequestParam String account) {
        cardService.withdrawMoney(principal, sum, account);
    }

    @PostMapping(path = "/transfer")
    public void transferMoney(Principal principal, @RequestParam Integer sum,
                              @RequestParam String fromAccount, @RequestParam String toAccount) {
        cardService.transferMoney(principal, sum, fromAccount, toAccount);
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
