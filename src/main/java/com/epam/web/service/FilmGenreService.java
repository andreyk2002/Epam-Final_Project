package com.epam.web.service;

import com.epam.web.dao.DaoException;
import com.epam.web.dao.DaoHelper;
import com.epam.web.dao.GenreDao;
import com.epam.web.dao.factory.DaoHelperFactory;
import com.epam.web.entity.Genre;

import java.util.List;

public class FilmGenreService {

    private final DaoHelperFactory helperFactory;

    public FilmGenreService(DaoHelperFactory helperFactory) {
        this.helperFactory = helperFactory;
    }

}
