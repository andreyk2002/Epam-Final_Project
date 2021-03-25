package com.epam.web.mapper;

import com.epam.web.entity.Identifiable;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMapper<T extends Identifiable> {
    static Object create(String table) {
        return null;
    }

    T map(ResultSet resultSet) throws SQLException;
}
