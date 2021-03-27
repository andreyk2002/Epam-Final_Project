package com.epam.web.dao;

import com.epam.web.entity.Role;
import com.epam.web.entity.User;
import com.epam.web.mapper.UserRowMapper;

import java.sql.Connection;
import java.util.Optional;

public class UserDaoImpl extends AbstractDao<User> implements UserDao {

    private final static String TABLE_NAME = "Users";
    private static final String LOGIN_QUERY = "SELECT * FROM Users WHERE login = ? AND password = MD5(?)";
    private static final String ADD_USER = "INSERT INTO Users(Login, Role, Rating, Password) VALUES(?, ?, ?, MD5(?))";



    public UserDaoImpl(Connection connection) {
        super(connection, new UserRowMapper(), TABLE_NAME);
    }

    @Override
    public Optional<User> getUserByLoginAndPassword
            (String login, String password) throws DaoException, WrongQueryException {

        return executeForSingleResult(LOGIN_QUERY, login, password);
    }

    @Override
    //TODO : create unmapper classe
    public void add(User item) throws DaoException {

        double rating = item.getRating();
        Role role = item.getRole();
        String login = item.getLogin();
        String password = item.getPassword();

        updateQuery(ADD_USER, login, role, rating, password);
    }

}
