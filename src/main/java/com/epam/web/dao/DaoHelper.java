package com.epam.web.dao;

import com.epam.web.connection.ConnectionPool;
import com.epam.web.connection.ProxyConnection;
import com.epam.web.dao.impl.GenreDaoImpl;
import com.epam.web.dao.impl.RatingDaoImpl;
import com.epam.web.dao.impl.ReviewDaoImpl;
import com.epam.web.dao.impl.UserDaoImpl;

import java.sql.SQLException;

public class DaoHelper implements AutoCloseable {

    private final ProxyConnection connection;

    public DaoHelper(ConnectionPool pool) throws DaoException {
        this.connection = pool.getConnection();
    }

    public GenreDao createGenreDao(){
        return new GenreDaoImpl(connection);
    }

    public UserDaoImpl createUserDao() {
        return new UserDaoImpl(connection);
    }

    public RatingDao createRatingDao() {
        return new RatingDaoImpl(connection);
    }

    public MovieDao createMovieDao() {
        GenreDao genreDao = createGenreDao();
        ReviewDao reviewDao = createReviewDao();
        RatingDao ratingDao = createRatingDao();
        return new MovieDao(connection, ratingDao, reviewDao, genreDao);
    }


    @Override
    public void close() {
        connection.close();
    }

    public void startTransaction() throws DaoException {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new DaoException();
        }
    }

    public void endTransaction() throws DaoException {
        try {
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            throw new DaoException();
        }
    }

    public ReviewDao createReviewDao() {
        UserDao dao = createUserDao();
        return new ReviewDaoImpl(connection, dao);
    }
}
