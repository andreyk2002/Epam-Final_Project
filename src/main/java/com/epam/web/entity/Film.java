package com.epam.web.entity;

import java.util.*;

public class Film {

    private final long id;
    private final String name;
    private final String imagePath;
    private final String description;
    private final Long genreId;

    public Film(Builder builder) {
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
        private long id = 0;
        private final String name;
        public long genreId;
        private String imagePath = "";
        private String description = "";

        public Builder( String name, long genreId) {
            this.name = name;
            this.genreId = genreId;
        }

        public  Builder withId(long id){
            this.id = id;
            return this;
        }

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder withImagePath(String imagePath) {
            this.imagePath = imagePath;
            return this;
        }


        public Film build() {
            return new Film(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Film)){
            return false;
        }

        Film film = (Film) o;

        if (id != film.id) {
            return false;
        }
        if (!Objects.equals(name, film.name)){
            return false;
        }
        if (!Objects.equals(imagePath, film.imagePath)) {
            return false;
        }
        if (!Objects.equals(description, film.description)){
            return false;
        }
        return Objects.equals(genreId, film.genreId);
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
