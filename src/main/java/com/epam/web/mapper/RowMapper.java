package com.epam.web.mapper;

import com.epam.web.dao.DaoException;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * RowMapper interface is responsible for parsing storage record
 * into instance of specified entity
 * @param <T> type of entity to which record should be parsed
 */

public interface RowMapper<T> {

    /**
     * Parses a record in the storage into specified entity
     * @param resultSet representation of record in the storage,
     * needed to be parsed
     * @return instance of specified type
     * @throws SQLException if record columns not matches to entity fields
     */
    T map(ResultSet resultSet) throws SQLException;
}
