package com.epam.web.dao;

import com.epam.web.entity.User;

import java.util.Optional;

public interface UserDao extends Dao<User> {

    Optional<User> getUserByLoginAndPassword(String login, String password) throws DaoException;

    void increaseRating(long userID) throws DaoException;

    void decreaseRating(long userID) throws DaoException;
}
