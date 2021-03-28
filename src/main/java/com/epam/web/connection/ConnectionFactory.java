package com.epam.web.connection;

import com.epam.web.dao.DaoException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/epamdb";

    public ProxyConnection create(ConnectionPool pool) throws DaoException {
        try {
//            ResourceBundle resource = ResourceBundle.getBundle("resource.database");
//            String url = resource.getString("url");
//            String user = resource.getString("user");
//            String password = resource.getString("password");
//            Properties properties = new Properties();
//            properties.put("user", user);
//            properties.put("password", password);
            Connection connection = DriverManager.getConnection(DB_URL, "root", "djpk03685v2");
            return new ProxyConnection(connection, pool);
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }

}
