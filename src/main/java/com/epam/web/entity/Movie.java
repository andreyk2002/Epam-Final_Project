package com.epam.web.entity;

import java.util.*;

public class Movie implements Identifiable {

    private final Long id;
    private final String name;
    private final String imagePath;
    private final String description;
    private final String genre;
    private final Double rating;
    private final List<Review>filmsReviews;

    public Movie(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.rating = builder.rating;
        this.genre = builder.genre;
        this.description = builder.description;
        this.imagePath = builder.imagePath;
        this.filmsReviews = builder.reviews;
    }

    @Override
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

    public static class Builder {
        private final long id;
        private final String name;
        private final String genre;
        private Double rating = 0.;
        private String imagePath = "";
        private String description = "";
        private List<Review>reviews = new ArrayList<>();

        public Builder(long id, String name, String genre) {
            this.id = id;
            this.name = name;
            this.genre = genre;
        }

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder withImagePath(String imagePath) {
            this.imagePath = imagePath;
            return this;
        }

        public Builder withRating(Double rating){
            this.rating = rating;
            return this;
        }

        public Builder withReviews(List<Review>filmsReviews){
            this.reviews = filmsReviews;
            return this;
        }

        public Movie build() {
            return new Movie(this);
        }
    }
}
