package com.epam.web.mapper;

import com.epam.web.dao.DaoException;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMapper<T> {

    T map(ResultSet resultSet) throws SQLException, DaoException;
}
