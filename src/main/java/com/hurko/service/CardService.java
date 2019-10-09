package com.hurko.service;

import com.hurko.model.CardEntity;

import java.security.Principal;
import java.util.List;

public interface CardService {

    void putMoney(Integer money, String number);

    void getMoney(Principal principal, Integer money, String number);

    void transferMoney(Principal principal, Integer money, String fromCardNumber, String toCardNumber);

    List<CardEntity> findAll();

    CardEntity findById(long id);

    void create(CardEntity entity);

    void update(long id, CardEntity entity);

    void delete(long id);
}
