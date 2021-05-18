package com.epam.web.dao;

import com.epam.web.entity.Film;
import com.epam.web.entity.Genre;
import java.util.List;

/**
 * GenreDao is marker-interface, for interaction with storage,
 * contains data about {@link Genre} entities
 *
 * @see Dao
 * @author Andrey Kuksa
 */

public interface GenreDao extends Dao<Genre> {

    /**
     * returns all genres for specified film
     * @param filmId id of the specified film
     * @return list of all genres, related to specified film
     * @throws DaoException if request to the storage is invalid
     */
    List<Genre>getByFilm(long filmId) throws DaoException;
}
