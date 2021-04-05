package com.epam.web.service;

import com.epam.web.dao.*;
import com.epam.web.dao.factory.DaoHelperFactory;
import com.epam.web.dao.impl.FilmDaoImpl;
import com.epam.web.dto.MovieDTO;
import com.epam.web.entity.Movie;
import com.epam.web.entity.Review;
import org.decimal4j.util.DoubleRounder;

import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FilmService {

    public static final int ROUND_PRECISION = 2;
    private final FilmDao filmDao;
    private final GenreDao genreDao;
    private final RatingDao ratingDao;
    private final ReviewDao reviewDao;

    public FilmService(DaoHelperFactory factory) throws ServiceException {
        try (DaoHelper helper = factory.create()) {
            this.genreDao = helper.createGenreDao();
            this.ratingDao = helper.createRatingDao();
            this.filmDao = helper.createMovieDao();
            this.reviewDao = helper.createReviewDao();
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public List<MovieDTO> getNextMovies(int pageNumb) throws ServiceException {
        try {
            List<MovieDTO> movieDTOs = new ArrayList<>();
            List<Movie> moviesForPage = filmDao.getMoviesForPage(pageNumb);
            for(Movie movie : moviesForPage){
                MovieDTO movieDTO = getMovieDTO(movie);
                movieDTOs.add(movieDTO);
            }
            return movieDTOs;
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }


    public int getPagesCount() throws ServiceException {
        try {
            return filmDao.getPagesCount();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public Optional<MovieDTO> getMovieById(Long id) throws DaoException {
        Optional<Movie> optionalMovie = filmDao.getById(id);
        if(optionalMovie.isPresent()){
            Movie movie = optionalMovie.get();
            MovieDTO movieDTO = getMovieDTO(movie);
            return Optional.of(movieDTO);
        }
        return Optional.empty();
    }

    private MovieDTO getMovieDTO(Movie movie) throws DaoException {
        long genreId = movie.getGenreId();
        Optional<String> optionalGenre = genreDao.getById(genreId);
        String genre = optionalGenre.orElse("");

        long movieId = movie.getId();
        double movieRating = ratingDao.getMovieRating(movieId);
        double ratingRounded = DoubleRounder.round(movieRating, ROUND_PRECISION);

        List<Review> filmReviews = reviewDao.getFilmReviews(movieId);
        return new MovieDTO(movie, genre, ratingRounded, filmReviews);
    }
}
