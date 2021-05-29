package com.epam.web.dto;

import com.epam.web.entity.Film;
import com.epam.web.entity.Genre;
import com.epam.web.entity.Review;
import com.google.common.base.Objects;

import java.util.List;

public class FilmDto {

    private final Long id;
    private final String name;
    private final String imagePath;
    private final String description;
    private final List<Genre> genres;
    private final double rating;
    private final List<Review> filmsReviews;
    private final boolean alreadyRated;

    public FilmDto(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.rating = builder.rating;
        this.genres = builder.genres;
        this.description = builder.description;
        this.imagePath = builder.imagePath;
        this.filmsReviews = builder.filmsReviews;
        this.alreadyRated = builder.alreadyRated;
    }

    public static class Builder {
        private long id;
        private String name;
        private String imagePath;
        private String description;
        private List<Genre> genres;
        private double rating;
        private List<Review> filmsReviews;
        private boolean alreadyRated;

        public Builder() {

        }

        public Builder withFilm(Film film) {
            this.id = film.getId();
            this.name = film.getName();
            this.description = film.getDescription();
            this.imagePath = film.getImagePath();
            return this;
        }


        public Builder withGenres(List<Genre> genres) {
            this.genres = genres;
            return this;
        }

        public Builder withRating(double rating) {
            this.rating = rating;
            return this;
        }

        public Builder withReviews(List<Review> reviews) {
            this.filmsReviews = reviews;
            return this;
        }

        public Builder withUserRated(boolean isRated) {
            this.alreadyRated = isRated;
            return this;
        }

        public FilmDto build() {
            return new FilmDto(this);
        }
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

    public List<Genre> getGenres() {
        return genres;
    }

    public boolean isAlreadyRated() {
        return alreadyRated;
    }

    public double getRating() {
        return rating;
    }

    public List<Review> getFilmsReviews() {
        return filmsReviews;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FilmDto)) {
            return false;
        }
        FilmDto filmDto = (FilmDto) o;
        return Double.compare(filmDto.rating, rating) == 0
                && alreadyRated == filmDto.alreadyRated
                && Objects.equal(id, filmDto.id)
                && Objects.equal(name, filmDto.name)
                && Objects.equal(imagePath, filmDto.imagePath)
                && Objects.equal(description, filmDto.description)
                && Objects.equal(genres, filmDto.genres)
                && Objects.equal(filmsReviews, filmDto.filmsReviews);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, name, imagePath, description, genres, rating, filmsReviews, alreadyRated);
    }
}


