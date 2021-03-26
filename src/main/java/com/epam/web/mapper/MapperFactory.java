package com.epam.web.mapper;

import com.epam.web.entity.Identifiable;
import com.epam.web.entity.Movie;
import com.epam.web.entity.User;

public class MapperFactory {

    public static RowMapper create(String table){
        switch (table){
            case User.TABLE_NAME:
                return new UserRowMapper();
            case Movie.TABLE_NAME:
                return new MovieRowMapper();
            default:
                throw new IllegalStateException("Unexpected value: " + table);
        }
    }
}
