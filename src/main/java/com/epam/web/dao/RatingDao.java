package com.epam.web.dao;

import com.epam.web.entity.Film;
import com.epam.web.entity.Genre;
import com.epam.web.entity.Rating;

import java.util.List;

/**
 * RatingDao provides functionality for interaction with storage,
 * which contains data about {@link Rating} entities
 *
 * @see Dao
 * @author Andrey Kuksa
 */


public interface RatingDao extends Dao<Rating> {

    /**
     * Add a new record for specified rating into the storage
     * @param rating rating instance, needed to be saved
     * @throws DaoException is request to storage is corrupted
     * If record for specified rating is already in the storage,
     *  new record won't be inserted and storage state remains the same
     */
    void addRating(Rating rating) throws DaoException;

    /**
     * Checks if storage contains record for specified film ID and user ID
     * @param filmId id from films storage, which defines record about film
     * @param userId id from users storage, which defines record about user
     * @return true is storage contains record for specified parameters,
     * false if there is no record for specified parameters
     * @throws DaoException if request to storage is corrupted
     */
    boolean hasRating(long filmId, long userId) throws DaoException;

    /**
     * Counts the average rating for specified film
     * @param filmId id from films storage, which defines record about film
     * @return average value of all ratings for specified film.
     * If film has no ratings result will be 0.
     * @throws DaoException if there is no film with specified id or
     * request to storage is corrupted
     */
    double getMovieRating(long filmId) throws DaoException;

    /**
     * Removes all ratings connected with specified film
     * @param filmId id of film, for which ratings records needed to be deleted.
     * @throws DaoException if request to storage is corrupted
     */
    void removeFilmsRatings(long filmId) throws DaoException;
}
