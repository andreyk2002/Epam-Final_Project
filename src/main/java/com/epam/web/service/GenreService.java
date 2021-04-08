package com.epam.web.service;

import com.epam.web.dao.DaoException;
import com.epam.web.dao.DaoHelper;
import com.epam.web.dao.GenreDao;
import com.epam.web.dao.factory.DaoHelperFactory;
import com.epam.web.entity.Genre;

import java.util.List;

public class GenreService {

    private final DaoHelperFactory helperFactory;

    public GenreService(DaoHelperFactory helperFactory) {
       this.helperFactory = helperFactory;
    }


    public List<Genre> getAllGenres() throws ServiceException {
        try(DaoHelper helper = helperFactory.create()) {
            GenreDao genreDao = helper.createGenreDao();
            return genreDao.getAll();
        } catch (DaoException e) {
          throw new ServiceException(e.getMessage(), e);
        }
    }
}
