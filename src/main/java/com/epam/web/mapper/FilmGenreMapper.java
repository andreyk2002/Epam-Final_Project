package com.epam.web.mapper;

import com.epam.web.entity.FilmGenre;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FilmGenreMapper implements RowMapper<FilmGenre> {


    public static final String FILM_ID = "filmId";
    public static final String GENRE_ID = "genreId";
    public static final String ID = "ID";

    @Override
    public FilmGenre map(ResultSet resultSet) throws SQLException {
        long filmId = resultSet.getLong(FILM_ID);
        long genreId = resultSet.getLong(GENRE_ID);
        long id = resultSet.getLong(ID);
        return new FilmGenre(id, genreId, filmId);
    }
}
