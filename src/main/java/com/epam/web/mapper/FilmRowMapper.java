package com.epam.web.mapper;

import com.epam.web.dao.DaoException;
import com.epam.web.entity.Film;
import com.epam.web.entity.Review;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class FilmRowMapper implements RowMapper<Film> {
    private static final String ID = "ID";
    private static final String NAME = "Name";
    private static final String IMAGE_PATH = "ImagePath";
    private static final String DESCRIPTION = "Description";


    public FilmRowMapper() {
    }

    @Override
    public Film map(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(ID);
        String name = resultSet.getString(NAME);
        String imagePath = resultSet.getString(IMAGE_PATH);
        String description = resultSet.getString(DESCRIPTION);


        return new Film.Builder()
                .withName(name)
                .withId(id)
                .withImagePath(imagePath)
                .withDescription(description)
                .build();
    }
}
