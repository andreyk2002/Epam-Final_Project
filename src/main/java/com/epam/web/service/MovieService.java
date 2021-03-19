package com.epam.web.service;

import com.epam.web.dao.ListBasedMovieDao;
import com.epam.web.dao.MovieDao;
import com.epam.web.entity.Movie;

import java.util.List;

public class MovieService {
    private static MovieDao dao = new ListBasedMovieDao();

    public static List<Movie> getAllMovies() {
        return dao.getAllMovies();
    }
}
