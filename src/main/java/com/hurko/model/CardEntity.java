package com.hurko.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

import javax.validation.constraints.Pattern;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "card")
@Accessors(chain = true)
public class CardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    @Pattern(regexp = "^[0-9]{16}", message = "invalid card number")
    private String number;

    @Column
    @Pattern(regexp = "^[0-9]{4}", message = "length must be 4 digits")
    private String password;

    @Column
    private int balance;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
