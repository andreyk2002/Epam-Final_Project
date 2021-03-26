package com.epam.web.service;

import com.epam.web.dao.DaoException;
import com.epam.web.dao.MovieDao;
import com.epam.web.entity.Movie;

import java.util.List;

public class MovieService {

    private MovieDao dao;

    public MovieService(MovieDao dao) {
        this.dao = dao;
    }

    public List<Movie> getNextMovies(int pageNumb) throws ServiceException {
        try {
            return dao.getMoviesForPage(pageNumb);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public int getPagesCount() throws ServiceException {
        try {
            return dao.getPagesCount();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
