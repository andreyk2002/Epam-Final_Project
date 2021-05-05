package com.epam.web.dao.impl;

import com.epam.web.dao.AbstractDao;
import com.epam.web.dao.DaoException;
import com.epam.web.dao.GenreDao;
import com.epam.web.entity.Genre;
import com.epam.web.mapper.GenreMapper;
import com.epam.web.mapper.RowMapper;

import java.sql.Connection;
import java.util.List;

public class GenreDaoImpl extends AbstractDao<Genre> implements GenreDao {
    public static final String TABLE_NAME = "genres";
    public static final String GET_BY_FILM = "SELECT * FROM Genres g JOIN filmsgenres f ON g.ID = f.GenreId WHERE f.FilmId = ?";

    public GenreDaoImpl(Connection connection) {
        super(connection, new GenreMapper(), TABLE_NAME);
    }

    @Override
    public List<Genre> getByFilm(long filmId) throws DaoException {
        return executeQuery(GET_BY_FILM, filmId);
    }
}
