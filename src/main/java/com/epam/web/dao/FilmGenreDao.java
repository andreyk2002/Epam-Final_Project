package com.epam.web.dao;

import com.epam.web.entity.FilmGenre;

import java.util.List;

public interface FilmGenreDao extends Dao<FilmGenre> {
    void addFilmsGenres(long filmId, List<Long> genresId) throws DaoException;

    void removeByFilmId(long id) throws DaoException;
}
