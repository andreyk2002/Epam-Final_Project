package com.epam.web.dao;

import com.epam.web.entity.Identifiable;
import com.epam.web.mapper.MapperFactory;
import com.epam.web.mapper.RowMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class AbstractDao<T extends Identifiable> implements Dao<T> {

    private final Connection connection;

    public AbstractDao(Connection connection) {
        this.connection = connection;
    }


    protected void updateQuery(String query, Object... params) throws DaoException {
        try (PreparedStatement statement = createStatement(query, params)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }

    protected List<T> executeQuery(String query, RowMapper<T> mapper, Object... params) throws DaoException {
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

    protected Optional<T> executeForSingleResult(String query, RowMapper<T> mapper, Object... params) throws DaoException, WrongQueryException {
        List<T> items = executeQuery(query, mapper, params);
        if (items.size() == 1) {
            T item = items.get(0);
            return Optional.of(item);
        } else if (items.size() > 0) {
            throw new WrongQueryException("More than one record was found");
        } else {
            return Optional.empty();
        }
    }

    private PreparedStatement createStatement(String query, Object... params) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(query);
        for (int i = 1; i <= params.length; i++) {
            statement.setObject(i, params[i - 1]);
        }
        return statement;
    }

    @Override
    public List<T> getAll() throws DaoException {
        String table = getTableName();
        RowMapper<T> mapper = (RowMapper<T>) MapperFactory.create(table);
        return executeQuery("SELECT * FROM " + table, mapper);
    }

    @Override
    public Optional<T> getById(long id) throws DaoException, WrongQueryException {
        String table = getTableName();
        RowMapper<T> mapper = (RowMapper<T>) MapperFactory.create(table);
        return executeForSingleResult("SELECT * FROM " + table, mapper, id);
    }

    @Override
    public void removeById(long id) throws Exception {
        String table = getTableName();
        updateQuery("DELETE * FROM WHERE ID = " + table, id);
    }

    protected int getRecordsCount() throws DaoException {
        String table = getTableName();
        String query = "SELECT COUNT(*) AS COUNT FROM " + table;
        try (PreparedStatement statement = createStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            return resultSet.next() ? resultSet.getInt("COUNT") : 0;
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }

    protected abstract String getTableName();

}
