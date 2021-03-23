package com.epam.web.dao;

import com.epam.web.entity.Movie;
import java.util.List;

public interface MovieDao {

    List<Movie>getNextMovies(int count, int start);

    List<Movie>getAllMovies();
}
