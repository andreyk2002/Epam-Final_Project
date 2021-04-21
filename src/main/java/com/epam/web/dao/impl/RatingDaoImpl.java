package com.epam.web.dao.impl;

import com.epam.web.dao.AbstractDao;
import com.epam.web.dao.DaoException;
import com.epam.web.dao.RatingDao;
import com.epam.web.entity.Rating;
import com.epam.web.mapper.RatingMapper;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class RatingDaoImpl extends AbstractDao<Rating> implements RatingDao {

    private static final String RATING_TABLE = "filmsratings";
    private static final String INSERT_QUERY = "INSERT INTO filmsratings(userID, filmID, Rating) VALUES (?, ?, ?)";
    private static final String FIND_RATING = "SELECT * FROM filmsratings WHERE userID = ? AND filmID = ?";
    private static final String GET_AVG_RATING = "SELECT AVG(Rating) AS Rating FROM filmsratings WHERE filmID = ?";
    private static final String RATING_COLUMN = "Rating";
    private static final String SELECT_BY_FILM = "SELECT * FROM filmsratings WHERE FilmID = ?";
    private static final String DELETE_BY_FILM_ID = "DELETE FROM filmsratings WHERE FilmID = ?";

    public RatingDaoImpl(Connection connection) {
        super(connection, new RatingMapper(), RATING_TABLE);
    }


    @Override
    public void addRating(Rating rating) throws DaoException {
        long filmID = rating.getFilmID();
        long userID = rating.getUserID();
        int score = rating.getRating();
        if (!hasRating(filmID, userID)) {
            updateQuery(INSERT_QUERY, userID, filmID, score);
        }
    }

    @Override
    public boolean hasRating(long filmId, long userId) throws DaoException {
        Optional<Rating> rating = executeForSingleResult(FIND_RATING, userId, filmId);
        return rating.isPresent();
    }

    @Override
    public double getMovieRating(long filmId) throws DaoException {
        return executeAvg(GET_AVG_RATING, RATING_COLUMN, filmId);
    }

    @Override
    public List<Rating> getRatingsByFilm(long filmId) throws DaoException {
        return executeQuery(SELECT_BY_FILM, filmId);
    }

    @Override
    public void removeFilmsRatings(long filmId) throws DaoException {
        updateQuery(DELETE_BY_FILM_ID, filmId);
    }

    @Override
    public Optional<Rating> getById(long id) {
        throw new UnsupportedOperationException("This is a table with complex primary key");
    }

    @Override
    public void removeById(long id) {
        throw new UnsupportedOperationException("This is a table with complex primary key");
    }
}
