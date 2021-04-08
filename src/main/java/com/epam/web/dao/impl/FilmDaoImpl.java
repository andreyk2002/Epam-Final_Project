package com.epam.web.dao.impl;

import com.epam.web.connection.ProxyConnection;
import com.epam.web.dao.AbstractDao;
import com.epam.web.dao.DaoException;
import com.epam.web.dao.FilmDao;
import com.epam.web.entity.Film;
import com.epam.web.mapper.MovieRowMapper;

import java.util.List;

public class FilmDaoImpl extends AbstractDao<Film> implements FilmDao {

    private static final String TABLE_NAME = "films";
    private static final int MOVIES_PER_PAGE = 5;
    private static final String ADD_MOVIE = "INSERT INTO films(Name, ImagePath, Description, GenreId)" +
            " VALUES (?, ?, ?, ?)";
    private static final String SELECT_MOVIES_IN_BOUNDS = "SELECT * FROM films LIMIT ? OFFSET ?";

    public FilmDaoImpl(ProxyConnection connection) {
        super(connection, new MovieRowMapper(), TABLE_NAME);
    }


    @Override
    public void save(Film item) throws DaoException {
        updateQuery(ADD_MOVIE, item.getName(), item.getImagePath(), item.getDescription(), item.getGenreId());
    }

    @Override
    public List<Film> getMoviesForPage(int pageNumber) throws DaoException {
        int offset = pageNumber * MOVIES_PER_PAGE;
        return executeQuery(SELECT_MOVIES_IN_BOUNDS, MOVIES_PER_PAGE, offset);
    }


    @Override
    public int getPagesCount() throws DaoException {
        return getRecordsCount() / MOVIES_PER_PAGE;
    }
}
