package com.epam.web.dao.impl;

import com.epam.web.dao.AbstractDao;
import com.epam.web.dao.DaoException;
import com.epam.web.dao.UserDao;
import com.epam.web.entity.User;
import com.epam.web.mapper.UserRowMapper;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl extends AbstractDao<User> implements UserDao {

    private final static String TABLE_NAME = "Users";
    private static final String LOGIN_QUERY = "SELECT * FROM Users WHERE login = ? AND password = MD5(?)";

    //TODO:remove duplication
    private static final String INCREMENT_RATING = "UPDATE Users SET Rating = Rating + 1 WHERE ID = ?";
    private static final String DECREMENT_RATING = "UPDATE Users SET Rating = Rating - 1 WHERE ID = ?";

    public UserDaoImpl(Connection connection) {
        super(connection, new UserRowMapper(), TABLE_NAME);
    }

    public Optional<User> getUserByLoginAndPassword
            (String login, String password) throws DaoException {
        return executeForSingleResult(LOGIN_QUERY, login, password);
    }

    @Override
    public void increaseRating(long userID) throws DaoException {
        updateQuery(INCREMENT_RATING, userID);
    }

    @Override
    public void decreaseRating(long userID) throws DaoException {
        updateQuery(DECREMENT_RATING, userID);
    }

}
