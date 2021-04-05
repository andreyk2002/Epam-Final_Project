package com.epam.web.dao;

import com.epam.web.entity.Review;

import java.util.List;

public interface ReviewDao extends Dao<Review> {

    void reviewFilm(long filmID, long userID, String review) throws DaoException;

    List<Review> getFilmReviews(long id) throws DaoException;
}
