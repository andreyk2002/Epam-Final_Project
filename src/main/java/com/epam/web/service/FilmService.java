package com.epam.web.service;

import com.epam.web.dao.*;
import com.epam.web.dao.factory.DaoHelperFactory;
import com.epam.web.dto.FilmDTO;
import com.epam.web.entity.Film;
import com.epam.web.entity.Genre;
import com.epam.web.entity.Review;
import com.epam.web.security.XssProtector;
import org.decimal4j.util.DoubleRounder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FilmService {

    public static final int ROUND_PRECISION = 2;
    private final XssProtector protect;
    private final FilmDao filmDao;
    private final GenreDao genreDao;
    private final RatingDao ratingDao;
    private final ReviewDao reviewDao;
    private final DaoHelperFactory daoHelperFactory;
    private FilmGenreDao filmGenreDao;

    public FilmService(DaoHelperFactory factory, XssProtector protect) throws ServiceException {
        this.daoHelperFactory = factory;
        try (DaoHelper helper = factory.create()) {
            this.protect = protect;
            this.genreDao = helper.createGenreDao();
            this.ratingDao = helper.createRatingDao();
            this.filmDao = helper.createMovieDao();
            this.reviewDao = helper.createReviewDao();
            this.filmGenreDao = helper.createFilmGenreDao();
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public List<FilmDTO> getNextMovies(int pageNumb) throws ServiceException {
        try {
            List<Film> moviesForPage = filmDao.getMoviesForPage(pageNumb);
            return getFilmDTOS(moviesForPage);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public List<FilmDTO> getMoviesByName(String filmName) throws ServiceException {
        try {
            List<Film> moviesByName = filmDao.getMoviesByName(filmName);
            return getFilmDTOS(moviesByName);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public List<FilmDTO> getByGenreName(String genreName) throws ServiceException {
        try{
            List<Film> moviesByGenre = filmDao.getMoviesByGenreName(genreName);
            return getFilmDTOS(moviesByGenre);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    private List<FilmDTO> getFilmDTOS(List<Film> moviesForPage) throws DaoException {
        List<FilmDTO> filmDTOS = new ArrayList<>();
        for (Film film : moviesForPage) {
            FilmDTO filmDTO = getMovieDTO(film);
            filmDTOS.add(filmDTO);
        }
        return filmDTOS;
    }

    public int getPagesCount() throws ServiceException {
        try {
            return filmDao.getPagesCount();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public Optional<FilmDTO> getMovieDTOById(long id, long userId) throws DaoException {
        Optional<Film> optionalMovie = filmDao.getById(id);
        if (optionalMovie.isPresent()) {
            Film film = optionalMovie.get();
            FilmDTO filmDTO = getMovieDTO(film);
            boolean hasRating = ratingDao.hasRating(id, userId);
            filmDTO.setRated(hasRating);
            return Optional.of(filmDTO);
        }
        return Optional.empty();
    }

    private FilmDTO getMovieDTO(Film film) throws DaoException {
        List<Genre> filmsGenres = genreDao.getByFilm(film.getId());
        long movieId = film.getId();
        double movieRating = ratingDao.getMovieRating(movieId);
        double ratingRounded = DoubleRounder.round(movieRating, ROUND_PRECISION);

        List<Review> filmReviews = reviewDao.getFilmReviews(movieId);
        return new FilmDTO(film, filmsGenres, ratingRounded, filmReviews);
    }

    public void saveFilm(Film film, List<Long> genresId) throws ServiceException {
        Film safeFilm = removeMalformedData(film);
        try(DaoHelper daoHelper = daoHelperFactory.create()) {
            daoHelper.startTransaction();
            long filmId = filmDao.saveAndGetID(safeFilm);
            filmGenreDao.addFilmsGenres(filmId, genresId);
            daoHelper.endTransaction();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public void removeById(long filmId) throws DaoException {
        filmGenreDao.removeByFilmId(filmId);
        reviewDao.removeFilmsReviews(filmId);
        ratingDao.removeFilmsRatings(filmId);
        filmDao.removeById(filmId);
    }

    public Optional<Film> getFilmById(long filmId) throws DaoException {
        return filmDao.getById(filmId);
    }

    public void updateFilm(Film updatedFilm, List<Long> newGenres) throws ServiceException {
        long id = updatedFilm.getId();
        try(DaoHelper daoHelper = daoHelperFactory.create()) {
            daoHelper.startTransaction();
            Optional<Film> film = filmDao.getById(id);
            Film safeFilm = removeMalformedData(updatedFilm);
            if (film.isEmpty()) {
                saveFilm(safeFilm, newGenres);
            }
            filmDao.updateFilm(safeFilm);
            filmGenreDao.removeByFilmId(id);
            filmGenreDao.addFilmsGenres(id, newGenres);
            daoHelper.endTransaction();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    private Film removeMalformedData(Film updatedFilm) {
        String description = updatedFilm.getDescription();
        String name = updatedFilm.getName();
        String safeDescription = protect.replaceMalformed(description);
        String safeName = protect.replaceMalformed(name);
        return new Film.Builder()
                .withId(updatedFilm.getId())
                .withName(safeName)
                .withDescription(safeDescription)
                .withImagePath(updatedFilm.getImagePath())
                .build();
    }

}
