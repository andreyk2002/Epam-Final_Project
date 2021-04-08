package com.epam.web.mapper;

import com.epam.web.dao.DaoException;
import com.epam.web.entity.Film;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MovieRowMapper implements RowMapper<Film> {
    private static final String ID = "ID";
    private static final String NAME = "Name";
    private static final String IMAGE_PATH = "ImagePath";
    private static final String DESCRIPTION = "Description";
    private static final String GENRE_ID = "GenreID";


    public MovieRowMapper() {
    }

    @Override
    public Film map(ResultSet resultSet) throws SQLException, DaoException {
        long id = resultSet.getLong(ID);
        String name = resultSet.getString(NAME);
        String imagePath = resultSet.getString(IMAGE_PATH);
        String description = resultSet.getString(DESCRIPTION);
        long genreId = resultSet.getLong(GENRE_ID);
        Film.Builder builder = new Film.Builder(name, genreId);
        return builder.withId(id)
                .withImagePath(imagePath)
                .withDescription(description)
                .build();
    }
}
