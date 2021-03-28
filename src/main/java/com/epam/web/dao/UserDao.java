package com.epam.web.dao;

import com.epam.web.entity.User;

import java.util.Optional;

public interface UserDao {

    Optional<User> getUserByLoginAndPassword(String login, String password)
            throws DaoException;
}
