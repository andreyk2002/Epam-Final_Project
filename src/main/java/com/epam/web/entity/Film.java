package com.epam.web.entity;

import java.util.Objects;

public class Film {

    private final long id;
    private final String name;
    private final String imagePath;
    private final String description;

    public Film(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.description = builder.description;
        this.imagePath = builder.imagePath;
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


    public static class Builder {
        private long id = 0;
        private String name;
        private String imagePath = "";
        private String description = "";

        public Builder() {

        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }


        public Builder withId(long id) {
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
        if (this == o) return true;
        if (!(o instanceof Film)) return false;

        Film film = (Film) o;

        if (id != film.id) return false;
        if (!Objects.equals(name, film.name)) return false;
        if (!Objects.equals(imagePath, film.imagePath)) return false;
        return Objects.equals(description, film.description);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (imagePath != null ? imagePath.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
