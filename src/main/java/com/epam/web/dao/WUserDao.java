package com.epam.web.dao;

import com.epam.web.entity.User;

import java.sql.*;
import java.util.Optional;

public class WUserDao {
    public Optional<User> findUserByNameAndPassword(String username, String password) throws DaoException {
        try {
            //Driver should called only one time
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/epamdb?user=root","root", "djpk03685v2" );

            try (PreparedStatement statement = connection.prepareStatement("SELECT ID, NAME FROM Users WHERE NAME = ? AND PASSWORD = MD5(?)")){
                    statement.setString(1, username);
                    statement.setString(2, password);

                    try(ResultSet resultSet = statement.executeQuery()){
                        if(resultSet.next()){
                            long id = resultSet.getLong("ID");
                            String name = resultSet.getString("NAME");
                            User user = null;
                            return Optional.of(user);
                        }
                    }
            }

        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        }
        return Optional.empty();
    }

}
