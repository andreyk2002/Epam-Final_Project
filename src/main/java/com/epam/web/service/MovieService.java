package com.epam.web.service;

import com.epam.web.dao.*;
import com.epam.web.dao.factory.DaoHelperFactory;
import com.epam.web.dto.MovieDTO;
import com.epam.web.entity.Movie;
import com.epam.web.entity.Review;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MovieService {

    private final MovieDao movieDao;
    private final GenreDao genreDao;
    private final RatingDao ratingDao;
    private final ReviewDao reviewDao;

    public MovieService(DaoHelperFactory factory) throws ServiceException {
        try (DaoHelper helper = factory.create()) {
            this.genreDao = helper.createGenreDao();
            this.ratingDao = helper.createRatingDao();
            this.movieDao = helper.createMovieDao();
            this.reviewDao = helper.createReviewDao();
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public List<MovieDTO> getNextMovies(int pageNumb) throws ServiceException {
        try {
            List<MovieDTO> movieDTOs = new ArrayList<>();
            List<Movie> moviesForPage = movieDao.getMoviesForPage(pageNumb);
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
            return movieDao.getPagesCount();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public Optional<MovieDTO> getMovieById(Long id) throws DaoException {
        Optional<Movie> optionalMovie = movieDao.getById(id);
        if(optionalMovie.isPresent()){
            Movie movie = optionalMovie.get();
            MovieDTO movieDTO = getMovieDTO(movie);
            return Optional.of(movieDTO);
        }
        return Optional.empty();
    }

    private MovieDTO getMovieDTO(Movie movie) throws DaoException {
        Long genreId = movie.getGenreId();
        Optional<String> optionalGenre = genreDao.getById(genreId);
        String genre = optionalGenre.orElse("");

        Long movieId = movie.getId();
        double movieRating = ratingDao.getMovieRating(movieId);

        List<Review> filmReviews = reviewDao.getFilmReviews(movieId);
        MovieDTO movieDTO = new MovieDTO(movie, genre, movieRating, filmReviews);
        return movieDTO;
    }
}
