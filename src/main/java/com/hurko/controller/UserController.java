package com.hurko.controller;

import com.hurko.model.dto.User;
import com.hurko.service.UserService;
import com.hurko.transformer.UserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserTransformer userTransformer;

    @GetMapping
    private List<User> findAll() {
        return userTransformer.buildUsers(userService.findAll());
    }

    @GetMapping(path = "/{id}")
    public User findById(@PathVariable("id") long id) {
        return userTransformer.buildUser(userService.findById(id));
    }

    @PostMapping
    public void create(@RequestBody User user) {
        userService.create(userTransformer.buildUserEntity(user));
    }

    @PutMapping(path = "/{id}")
    public void update(@PathVariable("id") long id, @RequestBody User user) {
        userService.update(id, userTransformer.buildUserEntity(user));
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable("id") long id) {
        userService.delete(id);
    }
}
