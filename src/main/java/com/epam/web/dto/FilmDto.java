package com.epam.web.dto;

import com.epam.web.entity.Film;
import com.epam.web.entity.Genre;
import com.epam.web.entity.Review;

import java.util.List;
import java.util.Objects;

public class FilmDto {

    private final Long id;
    private final String name;
    private final String imagePath;
    private final String description;
    private final List<Genre> genres;
    private final Double rating;
    private final List<Review> filmsReviews;

    public FilmDto(Film film, List<Genre> genres, double movieRating, List<Review> filmReviews) {
        this.id = film.getId();
        this.name = film.getName();
        this.rating = movieRating;
        this.genres = genres;
        this.description = film.getDescription();
        this.imagePath = film.getImagePath();
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

    public List<Genre> getGenres() {
        return genres;
    }

    public Double getRating() {
        return rating;
    }

    public List<Review> getFilmsReviews() {
        return filmsReviews;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (!(o instanceof FilmDto)){
            return false;
        }

        FilmDto filmDTO = (FilmDto) o;

        if (!Objects.equals(id, filmDTO.id)) return false;
        if (!Objects.equals(name, filmDTO.name)) return false;
        if (!Objects.equals(imagePath, filmDTO.imagePath)) return false;
        if (!Objects.equals(description, filmDTO.description)) return false;
        if (!Objects.equals(genres, filmDTO.genres)) return false;
        if (!Objects.equals(rating, filmDTO.rating)) return false;
        return Objects.equals(filmsReviews, filmDTO.filmsReviews);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (imagePath != null ? imagePath.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (genres != null ? genres.hashCode() : 0);
        result = 31 * result + (rating != null ? rating.hashCode() : 0);
        result = 31 * result + (filmsReviews != null ? filmsReviews.hashCode() : 0);
        return result;
    }
}


