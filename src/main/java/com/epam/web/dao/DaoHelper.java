package com.epam.web.dao;

import com.epam.web.connection.ConnectionPool;
import com.epam.web.connection.ProxyConnection;
import com.epam.web.mapper.UserRowMapper;

import java.sql.SQLException;

public class DaoHelper implements AutoCloseable {

    private final ProxyConnection connection;

    public DaoHelper(ConnectionPool pool) throws DaoException {
        this.connection = pool.getConnection();
    }

    public UserDaoImpl createUserDao() {
        UserRowMapper mapper = new UserRowMapper();
        return new UserDaoImpl(connection, mapper);
    }

//    public MoviewDao createMovieDao() {
//        MovieRowMapper mapper = new MovieRowMapper();
//        return new MoviewDaoImpl(connection, mapper);
//    }


    @Override
    public void close() throws Exception {
        connection.close();
    }

    public void startTransaction() throws DaoException {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new DaoException();
        }
    }


}
