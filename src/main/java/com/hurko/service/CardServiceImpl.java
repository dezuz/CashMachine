package com.hurko.service;

import com.hurko.controller.handler.IncorrectMoneyValue;
import com.hurko.controller.handler.IncorrectUserException;
import com.hurko.controller.handler.InvalidInputValueException;
import com.hurko.model.CardEntity;
import com.hurko.model.UserEntity;
import com.hurko.repository.CardRepository;
import com.hurko.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Objects;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void putMoney(Integer money, String number) {
        CardEntity card = cardRepository.findByNumber(number);
        if (Objects.nonNull(card)) {
            if (money % 100 == 0) {
                int newBalance = card.getBalance() + money;
                cardRepository.save(card.setBalance(newBalance));
            } else {
                throw new IncorrectMoneyValue("You can only put amount of money which is a multiple of 100");
            }
        } else {
            throw new InvalidInputValueException("Cannot find entity with current set of parameters");
        }
    }

    @Override
    public void getMoney(Principal principal, Integer money, String number) {
        UserEntity user = userRepository.findByName(principal.getName());
        CardEntity card = cardRepository.findByNumber(number);
        if (Objects.nonNull(user) && Objects.nonNull(card)) {
            if (card.getUser().getId() == user.getId()) {
                if (card.getBalance() > money && money % 100 == 0) {
                    int newBalance = card.getBalance() - money;
                    cardRepository.save(card.setBalance(newBalance));
                } else {
                    throw new IncorrectMoneyValue("Please write correct amount of money");
                }
            } else {
                throw new IncorrectUserException("You can't get money from not your card");
            }
        } else {
            throw new InvalidInputValueException("Cannot find entity with current set of parameters");
        }
    }

    @Override
    public void transferMoney(Principal principal, Integer money, String fromCardNumber, String toCardNumber) {
        UserEntity user = userRepository.findByName(principal.getName());
        CardEntity fromCard = cardRepository.findByNumber(fromCardNumber);
        CardEntity toCard = cardRepository.findByNumber(toCardNumber);
        if (Objects.nonNull(user) && Objects.nonNull(fromCard) && Objects.nonNull(toCard)) {
            if (fromCard.getUser().getId() == user.getId()) {
                if (fromCard.getBalance() > money) {
                    int newBalance = fromCard.getBalance() - money;
                    cardRepository.save(fromCard.setBalance(newBalance));
                    newBalance = toCard.getBalance() + money;
                    cardRepository.save(toCard.setBalance(newBalance));
                } else {
                    throw new IncorrectMoneyValue("There is not enough money on your balance");
                }
            } else {
                throw new IncorrectUserException("You can't get money from not your card");
            }
        } else {
            throw new InvalidInputValueException("Cannot find entity with current set of parameters");
        }
    }

    @Override
    public List<CardEntity> findAll() {
        return cardRepository.findAll();
    }

    @Override
    public CardEntity findById(long id) {
        return cardRepository.findById(id).orElseThrow(NullPointerException::new);
    }

    @Override
    public void create(CardEntity entity) {
        cardRepository.save(entity);
    }

    @Override
    public void update(long id, CardEntity entity) {
        cardRepository.save(entity.setId(id));
    }

    @Override
    public void delete(long id) {
        cardRepository.deleteById(id);
    }
}
