package com.epam.web.dao.impl;

import com.epam.web.connection.ProxyConnection;
import com.epam.web.dao.AbstractDao;
import com.epam.web.dao.DaoException;
import com.epam.web.dao.FilmGenreDao;
import com.epam.web.entity.FilmGenre;
import com.epam.web.mapper.FilmGenreMapper;
import com.epam.web.mapper.RowMapper;

import java.sql.Connection;
import java.util.List;

public class FilmGenreDaoImpl extends AbstractDao<FilmGenre> implements FilmGenreDao {

    private static   final String TABLE_NAME = "filmsgenres";
    public static final String INSERT_QUERY = "INSERT INTO filmsgenres(FilmId, GenreId) VALUES(?, ?)";
    public static final String DELETE_BY_FILM = "DELETE FROM filmsgenres WHERE FilmId = ?";

    public FilmGenreDaoImpl(Connection connection) {
        super(connection, new FilmGenreMapper(), TABLE_NAME);
    }

    @Override
    public void addFilmsGenres(long filmId, List<Long> genresId) throws DaoException {
        for(long genreId : genresId){
            updateQuery(INSERT_QUERY, filmId, genreId);
        }
    }

    @Override
    public void removeByFilmId(long id) throws DaoException {
        updateQuery(DELETE_BY_FILM, id);
    }
}
