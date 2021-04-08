package com.epam.web.dao;

import com.epam.web.entity.Rating;

import java.util.Optional;

public interface RatingDao extends Dao<Rating> {


    boolean addRating(long filmId, long userId, int rating) throws DaoException;

    boolean hasRating(long filmId, long userId) throws DaoException;

    double getMovieRating(long filmId) throws DaoException;

    Optional<Rating> getRatingForCheck(long filmId) throws DaoException;

    void removeFilmsRatings(long filmId) throws DaoException;
}
