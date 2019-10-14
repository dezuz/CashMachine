package com.hurko.service;

import com.hurko.model.CardEntity;

import java.security.Principal;
import java.util.List;

public interface CardService {

    void replenishmentMoney(Integer sum, String account);

    void withdrawMoney(Principal principal, Integer sum, String account);

    void transferMoney(Principal principal, Integer sum, String fromAccount, String toAccount);

    List<CardEntity> findAll();

    CardEntity findById(long id);

    void create(CardEntity entity);

    void update(long id, CardEntity entity);

    void delete(long id);
}
