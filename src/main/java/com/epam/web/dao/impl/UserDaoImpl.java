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
    private static final String ADD_USER = "INSERT INTO Users(Login, Role, Rating, Password) VALUES(?, ?, ?, MD5(?))";
    public static final String ALL_USERS_QUERY = "SELECT * FROM Users";
    public static final String USER_ID_QUERY = "SELECT * FROM Users WHERE ID = ?";
    public static final String DELETE_USER_QUERY = "DELETE * FROM Users WHERE ID = ";


    public UserDaoImpl(Connection connection) {
        super(connection, new UserRowMapper(), TABLE_NAME);
    }

    public Optional<User> getUserByLoginAndPassword
            (String login, String password) throws DaoException {
        return executeForSingleResult(LOGIN_QUERY, login, password);
    }

    @Override
    public List<User> getAll() throws DaoException {
        return executeQuery(ALL_USERS_QUERY);
    }

    @Override
    public Optional<User> getById(long id) throws DaoException {
        return executeForSingleResult(USER_ID_QUERY, id);
    }

    @Override
    public void removeById(long id) throws Exception {
        updateQuery(DELETE_USER_QUERY, id);
    }

}
