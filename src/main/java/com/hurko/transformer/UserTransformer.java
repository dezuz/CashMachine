package com.hurko.transformer;

import com.hurko.model.CardEntity;
import com.hurko.model.UserEntity;
import com.hurko.model.dto.Card;
import com.hurko.model.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class UserTransformer {

    @Autowired
    private CardTransformer cardTransformer;

    public List<User> buildUsers(List<UserEntity> entities) {
        return entities.stream().
                map(this::buildUser).
                collect(toList());
    }

    public User buildUser(UserEntity entity) {
        List<Card> cards = entity.getCards() == null
                ? Collections.emptyList()
                : cardTransformer.buildCards(entity.getCards());

        return new User()
                .setId(entity.getId())
                .setName(entity.getName())
                .setPassword(entity.getPassword())
                .setActive(entity.getActive())
                .setRole(entity.getRole())
                .setCards(cards);
    }

    public List<UserEntity> buildUserEntities(List<User> users) {
        return users.stream()
                .map(this::buildUserEntity)
                .collect(toList());
    }

    public UserEntity buildUserEntity(User user) {
        List<CardEntity> cards = user.getCards() == null
                ? Collections.emptyList()
                : cardTransformer.buildCardEntities(user.getCards());

        return new UserEntity()
                .setId(user.getId())
                .setName(user.getName())
                .setPassword(user.getPassword())
                .setActive(user.getActive())
                .setRole(user.getRole())
                .setCards(cards);
    }
}
