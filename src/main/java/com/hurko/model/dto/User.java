package com.hurko.model.dto;

import com.hurko.model.Role;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Setter
@Getter
@Accessors(chain = true)
public class User {
    private long id;
    private String name;
    private String password;
    private int active;
    private Role role;
    private List<Card> cards;
}
