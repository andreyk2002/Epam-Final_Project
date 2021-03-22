package com.epam.web.entity;

import java.util.Objects;

public class Movie {

    private final String name;
    private final String genre;
    private final double rating;
    private final String imageSrc;

    public Movie(String name, String genre, double rating, String imageSrc) {
        this.name = name;
        this.genre = genre;
        this.rating = rating;
        this.imageSrc = imageSrc;
    }

    public String getName() {
        return name;
    }

    public String getGenre() {
        return genre;
    }

    public double getRating() {
        return rating;
    }

    public String getImageSrc() {
        return imageSrc;
    }

    public long getId() {
        return 0;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }
        if (!(o instanceof Movie)) {
            return false;
        }

        Movie movie = (Movie) o;

        if (Double.compare(movie.rating, rating) != 0){
            return false;
        }
        if (!Objects.equals(name, movie.name)){
            return false;
        }
        if (!Objects.equals(genre, movie.genre)){
            return false;
        }
        return Objects.equals(imageSrc, movie.imageSrc);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = name != null ? name.hashCode() : 0;
        result = 31 * result + (genre != null ? genre.hashCode() : 0);
        temp = Double.doubleToLongBits(rating);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (imageSrc != null ? imageSrc.hashCode() : 0);
        return result;
    }

}
