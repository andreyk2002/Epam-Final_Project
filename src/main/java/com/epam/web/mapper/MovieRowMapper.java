package com.epam.web.mapper;

import com.epam.web.entity.Movie;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class MovieRowMapper implements RowMapper<Movie> {
    private static final String ID = "filmID";
    private static final String NAME = "filmName";
    private static final String IMAGE_PATH = "ImagePath";
    private static final String DESCRIPTION = "Description";
    private static final String GENRE_NAME = "genreName";
    private static final String RATING = "rating";

    @Override
    public Movie map(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(ID);
        String name = resultSet.getString(NAME);
        String imagePath = resultSet.getString(IMAGE_PATH);
        String description = resultSet.getString(DESCRIPTION);
        String genre = resultSet.getString(GENRE_NAME);
        Double rating = resultSet.getDouble(RATING);
        Movie.Builder builder = new Movie.Builder(id, name, genre);
        return builder.withRating(rating).withImagePath(imagePath).withDescription(description).build();
    }
}
