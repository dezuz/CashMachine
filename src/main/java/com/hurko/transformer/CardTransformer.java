package com.hurko.transformer;

import com.hurko.model.CardEntity;
import com.hurko.model.dto.Card;
import com.hurko.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class CardTransformer {

    @Autowired
    private UserService userService;

    public List<Card> buildCards(List<CardEntity> entities) {
        return entities.stream()
                .map(this::buildCard)
                .collect(toList());
    }

    public Card buildCard(CardEntity entity) {
        return new Card()
                .setId(entity.getId())
                .setNumber(entity.getNumber())
                .setPassword(entity.getPassword())
                .setBalance(entity.getBalance())
                .setUserId(entity.getUser().getId());
    }

    public List<CardEntity> buildCardEntities(List<Card> cards) {
        return cards.stream()
                .map(this::buildCardEntity)
                .collect(toList());
    }

    public CardEntity buildCardEntity(Card card) {
        return new CardEntity()
                .setId(card.getId())
                .setNumber(card.getNumber())
                .setPassword(card.getPassword())
                .setBalance(card.getBalance())
                .setUser(userService.findById(card.getUserId()));
    }
}
