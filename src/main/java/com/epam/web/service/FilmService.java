package com.epam.web.service;

import com.epam.web.dao.*;
import com.epam.web.dao.factory.DaoHelperFactory;
import com.epam.web.dto.MovieDTO;
import com.epam.web.entity.Film;
import com.epam.web.entity.Genre;
import com.epam.web.entity.Review;
import org.decimal4j.util.DoubleRounder;

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
            List<Film> moviesForPage = filmDao.getMoviesForPage(pageNumb);
            for(Film film : moviesForPage){
                MovieDTO movieDTO = getMovieDTO(film);
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
        Optional<Film> optionalMovie = filmDao.getById(id);
        if(optionalMovie.isPresent()){
            Film film = optionalMovie.get();
            MovieDTO movieDTO = getMovieDTO(film);
            return Optional.of(movieDTO);
        }
        return Optional.empty();
    }

    private MovieDTO getMovieDTO(Film film) throws DaoException {
        long genreId = film.getGenreId();
        Optional<Genre> optionalGenre = genreDao.getById(genreId);
        String genre = optionalGenre.map(Genre::getName).orElse("");

        long movieId = film.getId();
        double movieRating = ratingDao.getMovieRating(movieId);
        double ratingRounded = DoubleRounder.round(movieRating, ROUND_PRECISION);

        List<Review> filmReviews = reviewDao.getFilmReviews(movieId);
        return new MovieDTO(film, genre, ratingRounded, filmReviews);
    }

    public void saveFilm(Film film) throws DaoException {
        filmDao.save(film);
    }
}
