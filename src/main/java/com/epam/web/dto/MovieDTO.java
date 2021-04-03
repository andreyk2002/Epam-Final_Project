package com.epam.web.dto;

import com.epam.web.entity.Identifiable;
import com.epam.web.entity.Movie;
import com.epam.web.entity.Review;

import java.util.ArrayList;
import java.util.List;

public class MovieDTO {

    private final Long id;
    private final String name;
    private final String imagePath;
    private final String description;
    private final String genre;
    private final Double rating;
    private final List<Review> filmsReviews;

    public MovieDTO(Movie movie, String genre, double movieRating, List<Review> filmReviews) {
        this.id = movie.getId();
        this.name = movie.getName();
        this.rating = movieRating;
        this.genre = genre;
        this.description = movie.getDescription();
        this.imagePath = movie.getImagePath();
        this.filmsReviews = filmReviews;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getDescription() {
        return description;
    }

    public String getGenre() {
        return genre;
    }

    public Double getRating() {
        return rating;
    }

    public List<Review> getFilmsReviews() {
        return filmsReviews;
    }
}


