package com.epam.web.service;

import com.epam.web.dao.ListBasedMovieDao;
import com.epam.web.dao.MovieDao;
import com.epam.web.entity.Movie;

import java.util.List;

public class MovieService {
    private static final int FILMS_PER_PAGE = 5;
    private MovieDao dao = new ListBasedMovieDao();

    public MovieService(MovieDao dao) {
        this.dao = dao;
    }

    public List<Movie> getNextMovies(int pageNumb){
        return dao.getNextMovies(FILMS_PER_PAGE, pageNumb * FILMS_PER_PAGE);
    }

    public List<Movie> getAllMovies() {
        return dao.getAllMovies();
    }
}
