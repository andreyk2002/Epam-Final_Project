package com.epam.web.mapper;

import com.epam.web.entity.Rating;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RatingMapper implements RowMapper<Rating>{
    private static final String RATING = "Rating";
    private static final String USER_ID = "UserID";
    private static final String FILM_ID = "FilmID";


    @Override
    public Rating map(ResultSet resultSet) throws SQLException {
        int rating = resultSet.getInt(RATING);
        long userID = resultSet.getLong(USER_ID);
        long filmID = resultSet.getLong(FILM_ID);
        return new Rating(rating, userID, filmID);
    }
}
