package com.epam.web.dao.impl;

import com.epam.web.dao.*;
import com.epam.web.entity.Review;
import com.epam.web.mapper.RatingMapper;
import com.epam.web.mapper.ReviewMapper;
import com.epam.web.mapper.RowMapper;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class ReviewDaoImpl extends AbstractDao<Review> implements ReviewDao {
    private static final String TABLE_NAME = "reviews";
    private static final String INSERT_RATING = "INSERT INTO reviews(FilmID, UserID, Review) VALUES(?, ? ,?)";
    public static final String FIND_BY_FILM = "SELECT * FROM reviews WHERE FilmID = ?";

    public ReviewDaoImpl(Connection connection, UserDao dao) {
        super(connection, new ReviewMapper(dao), TABLE_NAME);
    }

    @Override
    public void rateFilm(Long filmID, Long userID, String review) throws DaoException {
        updateQuery(INSERT_RATING, filmID, userID, review);
    }

    @Override
    public List<Review> getFilmReviews(long id) throws DaoException {
        return executeQuery(FIND_BY_FILM, id);
    }

}
