package com.hurko.service;

import com.hurko.model.UserEntity;
import com.hurko.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    @Override
    public UserEntity findById(long id) {
        return userRepository.findById(id).orElseThrow(NullPointerException::new);
    }

    @Override
    public void create(UserEntity entity) {
        userRepository.save(entity);
    }

    @Override
    public void update(long id, UserEntity entity) {
        userRepository.save(entity.setId(id));
    }

    @Override
    public void delete(long id) {
        userRepository.deleteById(id);
    }
}
