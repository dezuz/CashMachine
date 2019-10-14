package com.hurko.controller.handler;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Setter
@Getter
public class IncorrectUserException extends RuntimeException {

    private String message;

    public IncorrectUserException(String message) {
        super(message);
        this.message = message;
        log.error("Incorrect user with message: {}", message);
    }
}
