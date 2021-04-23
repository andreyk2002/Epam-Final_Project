package com.epam.web.dao;

import com.epam.web.entity.Film;
import com.epam.web.entity.User;

import java.util.Optional;

/**
 * UserDao provides functionality for interaction with storage,
 * which contains data about {@link User} entities
 *
 * @see Dao
 * @author Andrey Kuksa
 */

public interface UserDao extends Dao<User> {

    /**
     * Searched for user with specified login and password
     * @param login login (name) of searched user
     * @param password password of searched user
     * @return instance of user wrapped in {@link Optional} class
     * if users with record with specified login and password is present
     * in the storage, {@code Optional.empty()} if there is no record for
     * specified parameters
     * @throws DaoException if request to the storage is invalid
     */

    Optional<User> getUserByLoginAndPassword(String login, String password) throws DaoException;

    /**
     * Changes rating of specified user to specified value
     * @param userId id of the user, which rating will be changed
     * @param newRating new rating of the user (in bounds 0 - 100)
     * @throws DaoException if request to the storage is invalid
     */

    void changeRating(long userId, double newRating) throws DaoException;

    /**
     * Changes rating of specified user by incrementing previous rating by one
     * @param userId id of the user, which rating will be incremented
     * @throws DaoException if request to the storage is invalid
     */
    void incrementRating(long userId) throws DaoException;

    /**
     * Changes rating of specified user by decrementing previous rating by one
     * @param userId id of the user, which rating will be decremented
     * @throws DaoException if request to the storage is invalid
     */
    void decrementRating(long userId) throws DaoException;

    /**
     * Removes or sets blocking on specified user
     * @param userId id of the user, which status will be changed
     * @param isBlocked specifies the user status. If value is
     * {@code true} user will be blocked, if value is {@code false}
     * blocking will be removed
     * @throws DaoException if request to the storage is invalid
     */
    void changeStatus(long userId, boolean isBlocked) throws DaoException;
}
