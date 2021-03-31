package com.epam.web.dao;

import com.epam.web.connection.ProxyConnection;
import com.epam.web.entity.Movie;
import com.epam.web.mapper.MovieRowMapper;

import java.util.List;
import java.util.Optional;

public class MovieDao extends AbstractDao<Movie> {

    private static final String VIEW_NAME = "movies_view";
    private static final String TABLE_NAME = "films";
    private static final int MOVIES_PER_PAGE = 5;
    private static final String ADD_MOVIE = "INSERT INTO Films(ID, Name, ImagePath, Description, GenreId)" +
            " VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_MOVIES_IN_BOUNDS = "SELECT * FROM movies_view LIMIT ? OFFSET ?";
    private static final String SELECT_MOVIE = "SELECT * FROM movies_view WHERE filmID = ?";
    private static final String SELECT_ALL = "SELECT * FROM movies_view";

    public MovieDao(ProxyConnection connection) {
        super(connection, new MovieRowMapper(), TABLE_NAME);
    }


    public void save(Movie item) throws DaoException {
        throw new UnsupportedOperationException();
    }

    public List<Movie> getMoviesForPage(int pageNumber) throws DaoException {
        int offset = pageNumber * MOVIES_PER_PAGE;
        return executeQuery(SELECT_MOVIES_IN_BOUNDS, MOVIES_PER_PAGE, offset);
    }

    @Override
    public Optional<Movie> getById(long id) throws DaoException {
        return executeForSingleResult(SELECT_MOVIE, id);
    }

    @Override
    public List<Movie> getAll() throws DaoException {
        return executeQuery(SELECT_ALL);
    }

    public int getPagesCount() throws DaoException {
        return getRecordsCount() / MOVIES_PER_PAGE;
    }
}
