package com.epam.web.dao;

import com.epam.web.entity.Movie;

import java.util.Arrays;
import java.util.List;

public class ListBasedMovieDao implements MovieDao {


    private static final  List<Movie>movies = Arrays.asList(
            new Movie("Inception", "Science fiction", 5.0, "static/img/movies/inception.jfif"),
            new Movie("Interstellar", "Science fiction", 4.8, "static/img/movies/interstellar.jfif"),
            new Movie("Terminator", "Science fiction/Action film", 5.0, "static/img/movies/terminator.jfif"),
            new Movie("Avatar", "Science fiction", 4.6, "static/img/movies/avatar.jpg"),
            new Movie("Aviator", "Drama", 4.7, "static/img/movies/aviator.jfif"),
            new Movie("The beautiful mind", "Drama/Biographic", 4.9, "static/img/movies/the_beautiful_mind.jfif"),
            new Movie("Pirates of the Caribbean pt. 1", "Action/Travelling", 4.7, "static/img/movies/pirates.jpg"),
            new Movie("Cinderella man", "Drama/Biographic", 4.7, "static/img/movies/CinderellaMain.jpg"));


    @Override
    public List<Movie> getNextMovies(int count, int start) {
        int totalEnd = movies.size() - 1;
        int currentEnd = start + count;
        int end = Math.min(totalEnd, currentEnd);
        return movies.subList(start, end);
    }

    @Override
    public List<Movie> getAllMovies() {
        return movies;
    }
}
