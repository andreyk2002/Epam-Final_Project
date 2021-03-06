package com.epam.web.dao;

import com.epam.web.mapper.RowMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class AbstractDao<T> implements Dao<T> {


    private final String tableName;
    private final RowMapper<T> mapper;
    private final Connection connection;


    public AbstractDao(Connection connection, RowMapper<T> mapper, String tableName) {
        this.tableName = tableName;
        this.mapper = mapper;
        this.connection = connection;
    }


    protected void updateQuery(String query, Object... params) throws DaoException {
        try (PreparedStatement statement = createStatement(query, params)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }


    protected List<T> executeQuery(String query, Object... params) throws DaoException {
        try (PreparedStatement statement = createStatement(query, params);
             ResultSet resultSet = statement.executeQuery()) {
            List<T> entities = new ArrayList<>();
            while (resultSet.next()) {
                T entity = mapper.map(resultSet);
                entities.add(entity);
            }
            return entities;
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }

    protected Optional<T> executeForSingleResult(String query, Object... params) throws DaoException {
        List<T> items = executeQuery(query, params);
        if (items.size() == 1) {
            T item = items.get(0);
            return Optional.of(item);
        } else if (items.size() > 1) {
            throw new DaoException("More than one record was found");
        } else {
            return Optional.empty();
        }
    }

    //TODO: is it good enough
    protected double executeAvg(String query, String columnName, Object... params) throws DaoException {
        try (PreparedStatement statement = createStatement(query, params);
             ResultSet resultSet = statement.executeQuery()) {
            resultSet.next();
            return resultSet.getDouble(columnName);
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }

    protected PreparedStatement createStatement(String query, Object... params) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        for (int i = 1; i <= params.length; i++) {
            statement.setObject(i, params[i - 1]);
        }
        return statement;
    }

    protected int getRecordsCount() throws DaoException {
        String query = "SELECT COUNT(*) AS COUNT FROM " + tableName;
        try (PreparedStatement statement = createStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            return resultSet.next() ? resultSet.getInt("COUNT") : 0;
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }

    @Override
    public List<T> getAll() throws DaoException {
        return executeQuery("SELECT * FROM " + tableName);
    }

    @Override
    public Optional<T> getById(long id) throws DaoException {
        return executeForSingleResult("SELECT * FROM " + tableName + " WHERE id=?", id);
    }

    @Override
    public void removeById(long id) throws DaoException {
        updateQuery("DELETE FROM " + tableName + " WHERE id=?", id);
    }
}
