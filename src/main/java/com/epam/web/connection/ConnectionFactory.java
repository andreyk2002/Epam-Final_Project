package com.epam.web.connection;

import com.epam.web.dao.DaoException;
import com.mysql.cj.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/epamdb";

    public static ProxyConnection create() throws DaoException {
        try {
            Connection connection = DriverManager.getConnection(DB_URL,"root", "djpk03685v2");
            return new ProxyConnection(connection);
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }

}
