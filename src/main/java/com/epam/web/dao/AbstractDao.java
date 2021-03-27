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

    private final String tableName;
    private final RowMapper<T> mapper;
    private final Connection connection;


    public AbstractDao(Connection connection, RowMapper<T> mapper,  String tableName) {
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

    protected Optional<T> executeForSingleResult(String query, Object... params) throws DaoException, WrongQueryException {
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
        return executeQuery("SELECT * FROM " + tableName, mapper);
    }

    @Override
    public Optional<T> getById(long id) throws DaoException, WrongQueryException {
        return executeForSingleResult("SELECT * FROM " + tableName, id);
    }

    @Override
    public void removeById(long id) throws Exception {
        updateQuery("DELETE * FROM WHERE ID = " + tableName, id);
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
    

}
