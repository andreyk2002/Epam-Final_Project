package com.epam.web.service;

import com.epam.web.dao.ListBasedMovieDao;
import com.epam.web.dao.MovieDao;
import com.epam.web.entity.Movie;

import java.util.List;

public class MovieService {
    private MovieDao dao = new ListBasedMovieDao();

    public MovieService(MovieDao dao) {
        this.dao = dao;
    }

    public List<Movie> getAllMovies() {
        return dao.getAllMovies();
    }
}
