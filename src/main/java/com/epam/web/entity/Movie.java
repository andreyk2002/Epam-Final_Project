package com.epam.web.entity;

public class Movie implements Identifiable {

    public static final  String TABLE_NAME = "Films";

    private final long id;
    private final String name;
    private final String imagePath;
    private final String description;

    public Movie(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.imagePath = builder.imagePath;
        this.description = builder.description;
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

    public static class Builder {

        private long id;
        private String name;
        private String imagePath;
        private String description;
        private static final Builder INSTANCE = new Builder();

        public static Builder getInstance() {
            return INSTANCE;
        }

        private Builder() {
        }

        public Builder withId(long id) {
            this.id = id;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
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

        public Movie build() {
            return new Movie(this);
        }
    }
}
