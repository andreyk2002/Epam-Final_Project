package com.epam.web.service;

import com.epam.web.entity.User;

import java.util.Optional;

public class UserService {

    public Optional<User> login(String username, String password) {
        return "admin".equals(username) && "admin".equals(password) ?
                Optional.of(new User("Andrey")) : Optional.empty();
    }
}
