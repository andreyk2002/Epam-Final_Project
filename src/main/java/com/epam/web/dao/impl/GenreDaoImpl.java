package com.epam.web.dao.impl;

import com.epam.web.dao.AbstractDao;
import com.epam.web.dao.GenreDao;
import com.epam.web.mapper.GenreMapper;
import com.epam.web.mapper.RowMapper;

import java.sql.Connection;

//TODO : import FILM_DTO

public class GenreDaoImpl extends AbstractDao<String> implements GenreDao {
    public static final String TABLE_NAME = "genres";

    public GenreDaoImpl(Connection connection) {
        super(connection, new GenreMapper(), TABLE_NAME);
    }
}
