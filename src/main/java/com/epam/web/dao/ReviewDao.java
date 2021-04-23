package com.epam.web.dao;

import com.epam.web.entity.Rating;
import com.epam.web.entity.Review;

import java.util.List;

/**
 * ReviewDao provides functionality for interaction with storage,
 * which contains data about {@link Review} entities
 *
 * @see Dao
 * @author Andrey Kuksa
 */

public interface ReviewDao extends Dao<Review> {

    /**
     * Adds a new review record to the storage
     * @param filmID id of the film, dedicated to review
     * @param userID id of user, which is review author
     * @param review content of review
     * @throws DaoException if request to the storage is invalid
     */

    void reviewFilm(long filmID, long userID, String review) throws DaoException;

    /**
     * Loads all reviews for specified film
     * @param filmId id for the film, for which reviews will be loaded
     * @return List of all reviews connected with specified film.
     * If there is no reviews about film, an empty List will be returned
     * @throws DaoException if request to the storage is invalid
     */
    List<Review> getFilmReviews(long filmId) throws DaoException;

    /**
     * Removes all reviews, connected with specified films
     * @param filmId id of film, for which reviews records needed to be deleted.
     * @throws DaoException if request to storage is corrupted
     */
    void removeFilmsReviews(long filmId) throws DaoException;
}
