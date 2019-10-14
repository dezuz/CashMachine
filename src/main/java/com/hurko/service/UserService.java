package com.hurko.service;

import com.hurko.model.UserEntity;

import java.util.List;

public interface UserService {

    List<UserEntity> findAll();

    UserEntity findById(long id);

    void create(UserEntity entity);

    void update(long id, UserEntity entity);

    void delete(long id);
}
