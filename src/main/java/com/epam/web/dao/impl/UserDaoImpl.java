package com.epam.web.dao.impl;

import com.epam.web.dao.AbstractDao;
import com.epam.web.dao.DaoException;
import com.epam.web.dao.UserDao;
import com.epam.web.entity.User;
import com.epam.web.mapper.UserRowMapper;

import java.sql.Connection;
import java.util.Optional;

public class UserDaoImpl extends AbstractDao<User> implements UserDao {

    private final static String TABLE_NAME = "Users";
    private static final String LOGIN_QUERY = "SELECT * FROM Users WHERE login = ? AND password = MD5(?)";
    private static final String CHANGE_RATING = "UPDATE Users SET Rating = ? WHERE ID = ?";
    private static final String CHANGE_STATUS = "UPDATE Users SET Blocked = ? WHERE ID = ?";
    public static final String INCREMENT_RATING = "UPDATE Users SET Rating = Rating + 1 WHERE ID = ?";
    public static final String DECREMENT_RATING = "UPDATE Users SET Rating = Rating - 1 WHERE ID = ?";

    public UserDaoImpl(Connection connection) {
        super(connection, new UserRowMapper(), TABLE_NAME);
    }

    public Optional<User> getUserByLoginAndPassword
            (String login, String password) throws DaoException {
        return executeForSingleResult(LOGIN_QUERY, login, password);
    }

    @Override
    public void changeRating(long userId, double newRating) throws DaoException {
        updateQuery(CHANGE_RATING, newRating, userId);
    }

    @Override
    public void incrementRating(long userId) throws DaoException {
        updateQuery(INCREMENT_RATING, userId);
    }

    @Override
    public void decrementRating(long userId) throws DaoException {
        updateQuery(DECREMENT_RATING, userId);
    }

    @Override
    public void changeStatus(long userId, boolean newStatus) throws DaoException {
        updateQuery(CHANGE_STATUS, newStatus, userId);
    }

}
