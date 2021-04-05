package com.epam.web.dao;

import com.epam.web.entity.Movie;

import java.util.List;

public interface FilmDao extends Dao<Movie> {

    void save(Movie item) throws DaoException;

    List<Movie> getMoviesForPage(int pageNumber) throws DaoException;

    int getPagesCount() throws DaoException;
}
