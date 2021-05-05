package com.epam.web.parser;

import com.epam.web.entity.Film;

import java.util.List;

public class ParseResult {

    private final List<Long> genresId;

    public ParseResult(Film film, List<Long> genresId){
        this.film = film;
        this.genresId = genresId;
    }

    public List<Long> getGenresId() {
        return genresId;
    }

    public Film getFilm() {
        return film;
    }

    private final Film film;
}
