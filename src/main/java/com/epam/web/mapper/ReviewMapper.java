package com.epam.web.mapper;

import com.epam.web.dao.DaoException;
import com.epam.web.dao.UserDao;
import com.epam.web.entity.Review;
import com.epam.web.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class ReviewMapper implements RowMapper<Review> {
    private static final String REVIEW = "review";
    private static final String USER_ID = "userId";
    private final UserDao userDao;


    public ReviewMapper(UserDao userDao) {
        this.userDao = userDao;
    }



    @Override
    public Review map(ResultSet resultSet) throws SQLException, DaoException {
        String userReview = resultSet.getString(REVIEW);
        Long userId = resultSet.getLong(USER_ID);
        Optional<User> user = userDao.getById(userId);
        String username = user.map(User::getLogin).orElse("");
        return new Review(username, userReview);
    }
}
