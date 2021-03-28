package com.epam.web.service;

import com.epam.web.dao.DaoException;
import com.epam.web.dao.DaoHelper;
import com.epam.web.dao.MovieDao;
import com.epam.web.dao.factory.DaoHelperFactory;
import com.epam.web.entity.Movie;

import java.util.List;

public class MovieService {

    private final MovieDao dao;

    public MovieService(DaoHelperFactory factory) throws ServiceException {
        try (DaoHelper helper = factory.create()) {
            this.dao = helper.createMovieDao();
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e);
        }
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
