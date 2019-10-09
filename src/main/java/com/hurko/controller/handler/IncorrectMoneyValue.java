package com.hurko.controller.handler;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Setter
@Getter
public class IncorrectMoneyValue extends RuntimeException {

    private String message;

    public IncorrectMoneyValue(String message) {
        super(message);
        this.message = message;
        log.error("Incorrect amount of money with message: {}", message);
    }
}
