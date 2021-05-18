package com.epam.web.dao;

import com.epam.web.entity.Film;
import com.epam.web.entity.FilmGenre;

import java.util.List;

/**
 * FilmDao provides functionality for interaction with storage,
 * which contains data films genres
 *
 * @see Dao
 * @see FilmDao
 * @see GenreDao
 * @author Andrey Kuksa
 */


public interface FilmGenreDao extends Dao<FilmGenre> {
    /**
     * Inserts records about relation between specified film and its genres
     * @param filmId id of specified film
     * @param genresId list of ids of all genres, which specified film has
     * @throws DaoException if request to the storage is invalid
     */
    void addFilmsGenres(long filmId, List<Long> genresId) throws DaoException;

    /**
     * removes all records about relation between specified film and all its genres
     * @param id id of the film which relations needed to be deleted
     * @throws DaoException if request to the storage is invalid
     */
    void removeByFilmId(long id) throws DaoException;
}
