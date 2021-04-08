package com.epam.web.dao.impl;

import com.epam.web.dao.AbstractDao;
import com.epam.web.dao.GenreDao;
import com.epam.web.entity.Genre;
import com.epam.web.mapper.GenreMapper;
import com.epam.web.mapper.RowMapper;

import java.sql.Connection;

public class GenreDaoImpl extends AbstractDao<Genre> implements GenreDao {
    public static final String TABLE_NAME = "genres";

    public GenreDaoImpl(Connection connection) {
        super(connection, new GenreMapper(), TABLE_NAME);
    }
}
