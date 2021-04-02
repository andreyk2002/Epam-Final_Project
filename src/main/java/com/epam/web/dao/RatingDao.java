package com.epam.web.dao;

public interface RatingDao extends Dao<Double> {


    boolean addRating(long filmId, long userId, int rating) throws DaoException;

    boolean hasRating(long filmId, long userId) throws DaoException;

    double getMovieRating(long filmId) throws DaoException;
}
