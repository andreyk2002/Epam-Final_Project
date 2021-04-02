package com.epam.web.mapper;

import com.epam.web.dao.DaoException;
import com.epam.web.dao.GenreDao;
import com.epam.web.dao.RatingDao;
import com.epam.web.dao.ReviewDao;
import com.epam.web.entity.Movie;
import com.epam.web.entity.Review;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class MovieRowMapper implements RowMapper<Movie> {
    private static final String ID = "ID";
    private static final String NAME = "Name";
    private static final String IMAGE_PATH = "ImagePath";
    private static final String DESCRIPTION = "Description";
    private static final String GENRE_ID = "GenreID";
    private final RatingDao ratingDao;
    private final ReviewDao reviewDao;
    private final GenreDao genreDao;

    public MovieRowMapper(RatingDao ratingDao, ReviewDao reviewDao, GenreDao genreDao) {
        this.ratingDao = ratingDao;
        this.reviewDao = reviewDao;
        this.genreDao = genreDao;
    }

    @Override
    public Movie map(ResultSet resultSet) throws SQLException, DaoException {
        long id = resultSet.getLong(ID);
        String name = resultSet.getString(NAME);
        String imagePath = resultSet.getString(IMAGE_PATH);
        String description = resultSet.getString(DESCRIPTION);
        double movieRating = ratingDao.getMovieRating(id);
        long genreId = resultSet.getLong(GENRE_ID);
        Optional<String> genre = genreDao.getById(genreId);

        List<Review> filmReviews = reviewDao.getFilmReviews(id);
        Movie.Builder builder = new Movie.Builder(id, name, genre.get());
        return builder.withRating(movieRating)
                .withDescription(description)
                .withImagePath(imagePath)
                .withReviews(filmReviews)
                .build();
    }
}
