package com.epam.web.entity;

public class FilmGenre {

    private final long id;
    private final long genreId;
    private final long filmId;

    public FilmGenre(long id, long genreId, long filmId) {
        this.id = id;
        this.genreId = genreId;
        this.filmId = filmId;
    }

    public long getId() {
        return id;
    }

    public long getGenreId() {
        return genreId;
    }

    public long getFilmId() {
        return filmId;
    }
}
