package com.hurko.controller.handler;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Setter
@Getter
public class InvalidInputValueException extends RuntimeException {

    private String message;

    public InvalidInputValueException(String message) {
        super(message);
        this.message = message;
        log.error("Incorrect input value with message: {}", message);
    }
}
