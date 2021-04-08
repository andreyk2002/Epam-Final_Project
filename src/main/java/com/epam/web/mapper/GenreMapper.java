package com.epam.web.mapper;

import com.epam.web.dao.DaoException;
import com.epam.web.entity.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GenreMapper implements RowMapper<Genre> {
    private static final String GENRE_NAME = "name";
    public static final String ID = "ID";

    @Override
    public Genre map(ResultSet resultSet) throws SQLException, DaoException {
        String name = resultSet.getString(GENRE_NAME);
        long id = resultSet.getLong(ID);
        return new Genre(id, name);
    }
}
