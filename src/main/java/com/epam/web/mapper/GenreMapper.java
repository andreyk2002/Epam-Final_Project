package com.epam.web.mapper;

import com.epam.web.dao.DaoException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GenreMapper implements RowMapper<String> {
    private static final String GENRE_NAME = "name";

    @Override
    public String map(ResultSet resultSet) throws SQLException, DaoException {
        return resultSet.getString(GENRE_NAME);
    }
}
