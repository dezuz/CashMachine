package com.hurko.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Getter
@Accessors(chain = true)
public class Card {
    private long id;
    private String number;
    private String password;
    private int balance;
    private long userId;
}
