package com.epam.web.entity;

import java.util.*;

public class Movie {

    private final long id;
    private final String name;
    private final String imagePath;
    private final String description;
    private final Long genreId;

    public Movie(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.description = builder.description;
        this.imagePath = builder.imagePath;
        this.genreId = builder.genreId;
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

    public long getGenreId() {
        return genreId;
    }

    public static class Builder {
        private final long id;
        private final String name;
        public long genreId;
        private String imagePath = "";
        private String description = "";

        public Builder(long id, String name, long genreId) {
            this.id = id;
            this.name = name;
            this.genreId = genreId;
        }

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder withImagePath(String imagePath) {
            this.imagePath = imagePath;
            return this;
        }


        public Movie build() {
            return new Movie(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Movie)){
            return false;
        }

        Movie movie = (Movie) o;

        if (id != movie.id) {
            return false;
        }
        if (!Objects.equals(name, movie.name)){
            return false;
        }
        if (!Objects.equals(imagePath, movie.imagePath)) {
            return false;
        }
        if (!Objects.equals(description, movie.description)){
            return false;
        }
        return Objects.equals(genreId, movie.genreId);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (imagePath != null ? imagePath.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (genreId != null ? genreId.hashCode() : 0);
        return result;
    }
}
