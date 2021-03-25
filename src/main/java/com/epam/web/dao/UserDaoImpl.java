package com.epam.web.dao;

import com.epam.web.entity.Role;
import com.epam.web.entity.User;
import com.epam.web.mapper.UserRowMapper;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl extends AbstractDao<User> implements UserDao {

    //TODO:Login not name
    private static final String FIND_BY_ID = "SELECT * FROM Users WHERE ID = (?)";
    private static final String FIND_BY_LOGIN_AND_PASSWORD = "SELECT * FROM Users WHERE login = ? AND password = MD5(?)";
    private static final String DELETE_BY_ID_QUERY = "DELETE * FROM Users WHERE ID = ?";
    private static final String SELECT_ALL = "SELECT * FROM Users";
    private static final String ADD_USER = "INSERT INTO Users(Login, Role, Rating, Password) VALUES(?, ?, ?, MD5(?))";

    private UserRowMapper mapper;

    public UserDaoImpl(Connection connection, UserRowMapper mapper) {
        super(connection);
        this.mapper = mapper;
    }

    @Override
    public Optional<User> getUserByLoginAndPassword
            (String login, String password) throws DaoException, WrongQueryException {

        UserRowMapper mapper = new UserRowMapper();
        return executeForSingleResult(FIND_BY_LOGIN_AND_PASSWORD,
                mapper, login, password);
    }

    @Override
    public List<User> getAll() throws DaoException {
        return executeQuery(SELECT_ALL, mapper);
    }

    @Override
    protected String getTableName() {
        return User.getTable();
    }

    @Override
    public Optional<User> getById(long id) throws WrongQueryException, DaoException {
        return executeForSingleResult(FIND_BY_ID, mapper, id);
    }

    @Override
    public void add(User item) throws DaoException {
        double rating = item.getRating();
        Role role = item.getRole();
        String login = item.getLogin();
        String password = item.getPassword();

        updateQuery(ADD_USER, login, role, rating, password);
    }

    @Override
    public void removeById(long id) throws DaoException {
        updateQuery(DELETE_BY_ID_QUERY, id);
    }
}
