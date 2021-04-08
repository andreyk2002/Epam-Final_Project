package com.epam.web.dao;

import com.epam.web.connection.ConnectionPool;
import com.epam.web.connection.ProxyConnection;
import com.epam.web.dao.impl.*;

import java.sql.SQLException;

public class DaoHelper implements AutoCloseable {

    private final ProxyConnection connection;

    public DaoHelper(ConnectionPool pool) {
        this.connection = pool.getConnection();
    }

    public GenreDao createGenreDao(){
        return new GenreDaoImpl(connection);
    }

    public UserDao createUserDao() {
        return new UserDaoImpl(connection);
    }

    public RatingDao createRatingDao() {
        return new RatingDaoImpl(connection);
    }

    public FilmDao createMovieDao() {
        return new FilmDaoImpl(connection);
    }

    public ReviewDao createReviewDao() {
        return new ReviewDaoImpl(connection);
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


}
