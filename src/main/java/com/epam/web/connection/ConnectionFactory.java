package com.epam.web.connection;

import com.epam.web.dao.DaoException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;

public class ConnectionFactory {

    //Avoid multiple reading properties (constructor)
    //transaction on Join
    public ProxyConnection create(ConnectionPool pool) throws DaoException {
        try {
            ResourceBundle resource = ResourceBundle.getBundle("database");
            String url = resource.getString("url");
            String user = resource.getString("user");
            String password = resource.getString("password");
            Properties properties = new Properties();
            properties.put("user", user);
            properties.put("password", password);
            Connection connection = DriverManager.getConnection(url, properties);
            return new ProxyConnection(connection, pool);
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }

}
