package com.epam.web.dao.impl;

import com.epam.web.dao.AbstractDao;
import com.epam.web.dao.DaoException;
import com.epam.web.dao.ReviewDao;
import com.epam.web.entity.Review;
import com.epam.web.mapper.ReviewMapper;

import java.sql.Connection;
import java.util.List;

public class ReviewDaoImpl extends AbstractDao<Review> implements ReviewDao {
    private static final String TABLE_NAME = "reviews";
    private static final String INSERT_RATING = "INSERT INTO reviews(FilmID, UserID, Review) VALUES(?, ? ,?)";
    public static final String FIND_BY_FILM = "SELECT *, login AS username FROM reviews r JOIN users u ON r.userID = u.ID"
            + " WHERE r.FilmID = ?";

    public ReviewDaoImpl(Connection connection) {
        super(connection, new ReviewMapper(), TABLE_NAME);
    }

    @Override
    public void reviewFilm(long filmID, long userID, String review) throws DaoException {
        updateQuery(INSERT_RATING, filmID, userID, review);
    }

    @Override
    public List<Review> getFilmReviews(long id) throws DaoException {
        return executeQuery(FIND_BY_FILM, id);
    }



}
