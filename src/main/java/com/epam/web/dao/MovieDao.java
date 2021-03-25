package com.epam.web.dao;

import com.epam.web.entity.Movie;

import java.util.List;

public interface MovieDao {


    List<Movie> getNextMovies(int filmsPerPage, int pageNumber);

    int getPagesCount(int filmsPerPage);

    List<Movie> getAllMovies();

}
