package com.epam.web.mapper;

import com.epam.web.entity.Role;
import com.epam.web.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;

public class UserRowMapper implements RowMapper<User> {

    private static final String ID = "ID";
    private static final String LOGIN = "login";
    private static final String ROLE = "role";
    private static final String RATING = "rating";

    @Override
    public User map(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(ID);
        String login = resultSet.getString(LOGIN);
        String roleString = resultSet.getString(ROLE);
        Role userRole = Role.valueOf(roleString.toUpperCase());
        int rating = resultSet.getInt(RATING);
        return new User(id , login, rating, userRole);
    }
}
