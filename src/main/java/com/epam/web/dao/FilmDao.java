package com.epam.web.dao;

import com.epam.web.entity.Film;

import java.util.List;

public interface FilmDao extends Dao<Film> {

    void save(Film item) throws DaoException;

    List<Film> getMoviesForPage(int pageNumber) throws DaoException;

    int getPagesCount() throws DaoException;

    void updateFilm(Film updatedFilm) throws DaoException;
}
