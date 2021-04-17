package com.epam.web.dao;

import com.epam.web.entity.User;

import java.util.Optional;

public interface UserDao extends Dao<User> {

    Optional<User> getUserByLoginAndPassword(String login, String password) throws DaoException;


    void changeRating(long userId, double newRating) throws DaoException;

    void incrementRating(long userId) throws DaoException;

    void decrementRating(long userId) throws DaoException;

    void changeStatus(long userId, boolean newStatus) throws DaoException;
}
