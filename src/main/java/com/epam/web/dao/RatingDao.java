package com.epam.web.dao;

import com.epam.web.entity.Rating;

import java.util.List;

public interface RatingDao extends Dao<Rating> {


    void addRating(Rating rating) throws DaoException;

    boolean hasRating(long filmId, long userId) throws DaoException;

    double getMovieRating(long filmId) throws DaoException;

    List<Rating> getRatingsByFilm(long filmId) throws DaoException;

    void removeFilmsRatings(long filmId) throws DaoException;
}
