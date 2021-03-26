package com.epam.web.dao;

import com.epam.web.connection.ProxyConnection;
import com.epam.web.entity.Movie;
import com.epam.web.mapper.MovieRowMapper;
import com.epam.web.mapper.RowMapper;

import java.util.List;

public class MovieDaoImpl extends AbstractDao<Movie> implements MovieDao {

    private static final int MOVIES_PER_PAGE = 5;
    private static final String ADD_MOVIE = "INSERT INTO Films(ID, Name, ImagePath, Description, GenreId)" +
            " VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_MOVIES_IN_BOUNDS = "SELECT * FROM Films LIMIT ? OFFSET ?";

    public MovieDaoImpl(ProxyConnection connection) {
        super(connection);
    }

    @Override
    protected String getTableName() {
        return Movie.TABLE_NAME;
    }


    @Override
    public void add(Movie item) throws DaoException {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Movie> getMoviesForPage(int pageNumber) throws DaoException {
        MovieRowMapper mapper = new MovieRowMapper();
        int offset = pageNumber * MOVIES_PER_PAGE;
        return executeQuery(SELECT_MOVIES_IN_BOUNDS, mapper, MOVIES_PER_PAGE, offset);
    }

    @Override
    public int getPagesCount() throws DaoException {
        return getRecordsCount();
    }
}
