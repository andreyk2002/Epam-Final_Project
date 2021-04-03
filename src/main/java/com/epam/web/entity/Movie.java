package com.epam.web.entity;

import java.util.*;

public class Movie implements Identifiable {

    private final Long id;
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

    public Long getGenreId() {
        return genreId;
    }

    public static class Builder {
        private final long id;
        private final String name;
        public Long genreId;
        private String imagePath = "";
        private String description = "";

        public Builder(long id, String name, Long genreId) {
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
}
