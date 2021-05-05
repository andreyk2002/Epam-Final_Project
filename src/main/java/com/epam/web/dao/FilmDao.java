package com.epam.web.dao;

import com.epam.web.entity.Film;

import java.util.List;

/**
 * FilmDao provides functionality for interaction with storage,
 * which contains data about {@link Film} entities
 *
 * @see Dao
 * @author Andrey Kuksa
 */

public interface FilmDao extends Dao<Film> {

    /**
     * Save an instance of film into the storage
     * @param item instance of film, needed to be saved
     * @throws DaoException is request to storage is corrupted or record for
     * specified item is already in the storage
     */
    void save(Film item) throws DaoException;

    /**
     * Save an instance of film into the storage
     * @param film instance of film, needed to be saved
     * @throws DaoException is request to storage is corrupted or record for
     * specified item is already in the storage
     * @return ID of inserted film
     */
    long saveAndGetID(Film film) throws DaoException;
    /**
     * Loads films for specified page number
     * @param pageNumber number of page, on which received films will be displayed
     * @return List instances of films in range N * (pageNumber - 1) .. N * pageNumber,
     * where N - is amount of films per one page. Concrete value of films per page may depends on
     * implementation
     * @throws DaoException is request to storage is corrupted
     */
    List<Film> getMoviesForPage(int pageNumber) throws DaoException;

    /**
     *  Defines the films pages count
     * @return count pages with film, where page is range of films, described
     *  in {@link FilmDao#getMoviesForPage(int)} method
     * @throws DaoException is request to storage is corrupted
     */
    int getPagesCount() throws DaoException;

    /**
     * Update record for specified film.
     * If there is not record for specified film, nothing happens
     * @param updatedFilm instance of film, needed to be updated
     * @throws DaoException if request to storage is corrupted
     */

    void updateFilm(Film updatedFilm) throws DaoException;


    /**
     * Searches films which name contains specified string
     * @param searchString string to be contained in film name
     * @return List of films which name contained specified string
     * @throws DaoException if request to storage is corrupted
     */
    List<Film> getMoviesByName(String searchString) throws DaoException;

    /**
     * Searches films for specified genre
     * @param genreId id of needed film genre
     * @return List of films which genre id is equal to specified genreId
     * @throws DaoException if request to storage is corrupted
     */

    List<Film> getMoviesByGenreName(String genreId) throws DaoException;
}
