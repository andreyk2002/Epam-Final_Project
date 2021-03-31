package com.epam.web.mapper;

import com.epam.web.entity.Identifiable;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RatingMapper implements RowMapper<Double>{
    private final String RATING = "Rating";

    @Override
    public Double map(ResultSet resultSet) throws SQLException {
        return resultSet.getDouble(RATING);
    }
}
