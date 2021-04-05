package com.epam.web.mapper;

import com.epam.web.dao.DaoException;
import com.epam.web.entity.Review;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReviewMapper implements RowMapper<Review> {
    private static final String REVIEW = "review";
    private static final String USERNAME = "username";
    private static final String ID = "ID";


    @Override
    public Review map(ResultSet resultSet) throws SQLException, DaoException {
        String userReview = resultSet.getString(REVIEW);
        String username = resultSet.getString(USERNAME);
        long id = resultSet.getLong(ID);
        return new Review(username, userReview, id);
    }
}