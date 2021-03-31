package com.epam.web.dao;

import com.epam.web.mapper.RatingMapper;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class RatingDaoImp extends AbstractDao<Double> implements RatingDao {
    private static final String RATING_TABLE = "filmsratings";
    private static final String INSERT_QUERY = "INSERT INTO filmsratings(userID, filmID, Rating) VALUES (?, ?, ?)";
    public static final String FIND_RATING = "SELECT * FROM filmsratings WHERE userID = ? AND filmID = ?";


    public RatingDaoImp(Connection connection) {
        super(connection, new RatingMapper(), RATING_TABLE);
    }


    @Override
    public boolean addRating(long filmId, long userId, int rating) throws DaoException {
        if(!hasRating(filmId, userId)) {
            updateQuery(INSERT_QUERY, userId, filmId, rating);
            return true;
        }
        return false;
    }

    @Override
    public boolean hasRating(long filmId, long userId) throws DaoException {
        Optional<Double> rating = executeForSingleResult(FIND_RATING, userId, filmId);
        return rating.isPresent();
    }


    @Override
    public List<Double> getAll() throws DaoException {
        throw new UnsupportedOperationException("This is a table with complex primary key");
    }



    @Override
    public Optional<Double> getById(long id) throws Exception {
        throw new UnsupportedOperationException("This is a table with complex primary key");
    }

    @Override
    public void removeById(long id) throws Exception {
        throw new UnsupportedOperationException("This is a table with complex primary key");
    }

}
