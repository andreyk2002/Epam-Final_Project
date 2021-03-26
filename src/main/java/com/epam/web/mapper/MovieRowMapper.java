package com.epam.web.mapper;

import com.epam.web.entity.Movie;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MovieRowMapper implements RowMapper<Movie> {
    private static final String ID = "ID";
    private static final String NAME = "Name";
    private static final String IMAGE_PATH = "ImagePath";
    private static final String DESCRIPTION = "Description";
    private static final String GENRE_ID = "GenreID";

    @Override
    public Movie map(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("ID");
        String name = resultSet.getString(NAME);
        String imagePath = resultSet.getString(IMAGE_PATH);
        String description = resultSet.getString(DESCRIPTION);
        long genreID = resultSet.getLong(GENRE_ID);

        return Movie.Builder.getInstance()
                .withId(id)
                .withName(name)
                .withDescription(description)
                .withImagePath(imagePath)
                .build();
    }
}
