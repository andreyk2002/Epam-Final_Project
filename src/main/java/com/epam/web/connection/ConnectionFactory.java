package com.epam.web.connection;

import com.epam.web.dao.DaoException;
import com.mysql.cj.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private static boolean driverRegistered = false;

    public static ProxyConnection create() throws DaoException {
        try {
            if (!driverRegistered) {

                DriverManager.registerDriver(new Driver());
                driverRegistered = true;
            }
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/epamdb?user=root", "root", "djpk03685v2");
            return new ProxyConnection(connection);
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }

}
