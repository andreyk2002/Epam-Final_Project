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
    private final DaoHelperFactory factory;

    public FilmService(DaoHelperFactory factory, XssProtector protect) throws ServiceException {
        this.factory = factory;
        this.protect = protect;
    }

    public List<FilmDTO> getPage(int pageNumb) throws ServiceException {
        try(DaoHelper helper =  factory.create()) {
            FilmDao filmDao = helper.createFilmDao();
            List<Film> moviesForPage = filmDao.getMoviesForPage(pageNumb);
            return getFilmsDto(moviesForPage);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public List<FilmDTO> getByName(String filmName) throws ServiceException {
        try(DaoHelper helper =  factory.create()) {
            FilmDao filmDao = helper.createFilmDao();
            List<Film> moviesByName = filmDao.getMoviesByName(filmName);
            return getFilmsDto(moviesByName);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public List<FilmDTO> getByGenreName(String genreName) throws ServiceException {
        try(DaoHelper helper =  factory.create()) {
            FilmDao filmDao = helper.createFilmDao();
            List<Film> filmsByGenre = filmDao.getByGenreName(genreName);
            return getFilmsDto(filmsByGenre);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    private List<FilmDTO> getFilmsDto(List<Film> moviesForPage) throws DaoException {
        List<FilmDTO> filmsDto = new ArrayList<>();
        for (Film film : moviesForPage) {
            FilmDTO filmDTO = getMovieDTO(film);
            filmsDto.add(filmDTO);
        }
        return filmsDto;
    }

    public int getPagesCount() throws ServiceException {
        try(DaoHelper helper =  factory.create()) {
            FilmDao filmDao = helper.createFilmDao();
            return filmDao.getPagesCount();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public Optional<FilmDTO> getFilmDtoById(long id) throws DaoException {
        try(DaoHelper helper =  factory.create()) {
            FilmDao filmDao = helper.createFilmDao();
            Optional<Film> optionalMovie = filmDao.getById(id);
            if (optionalMovie.isPresent()) {
                Film film = optionalMovie.get();
                FilmDTO filmDTO = getMovieDTO(film);
                return Optional.of(filmDTO);
            }
        }
        return Optional.empty();
    }

    private FilmDTO getMovieDTO(Film film) throws DaoException {
        try(DaoHelper helper =  factory.create()) {
            RatingDao ratingDao = helper.createRatingDao();
            ReviewDao reviewDao = helper.createReviewDao();
            GenreDao genreDao = helper.createGenreDao();
            long genreId = film.getGenreId();
            Optional<Genre> optionalGenre = genreDao.getById(genreId);
            String genre = optionalGenre.map(Genre::getName).orElse("");
            long movieId = film.getId();
            double movieRating = ratingDao.getMovieRating(movieId);
            double ratingRounded = DoubleRounder.round(movieRating, ROUND_PRECISION);
            List<Review> filmReviews = reviewDao.getFilmReviews(movieId);
            return new FilmDTO(film, genre, ratingRounded, filmReviews);
        }
    }

    public void saveFilm(Film film) throws ServiceException {
        Film safeFilm = removeMalformedData(film);
        try(DaoHelper helper =  factory.create()) {
            FilmDao filmDao = helper.createFilmDao();
            filmDao.save(safeFilm);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public void removeById(long filmId) throws ServiceException {
        try(DaoHelper helper =  factory.create()) {
            helper.startTransaction();
            FilmDao filmDao = helper.createFilmDao();
            ReviewDao reviewDao = helper.createReviewDao();
            RatingDao ratingDao = helper.createRatingDao();
            reviewDao.removeFilmsReviews(filmId);
            ratingDao.removeFilmsRatings(filmId);
            filmDao.removeById(filmId);
            helper.endTransaction();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public Optional<Film> getFilmById(long filmId) throws DaoException {
        try(DaoHelper helper =  factory.create()) {
            FilmDao filmDao = helper.createFilmDao();
            return filmDao.getById(filmId);
        }
    }

    public void updateFilm(Film updatedFilm) throws ServiceException {
        long id = updatedFilm.getId();
        try(DaoHelper helper =  factory.create()) {
            FilmDao filmDao = helper.createFilmDao();
            Optional<Film> film = filmDao.getById(id);
            Film safeFilm = removeMalformedData(updatedFilm);
            if (film.isEmpty()) {
                saveFilm(safeFilm);
            }
            filmDao.updateFilm(safeFilm);
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
                .withGenreId(updatedFilm.getGenreId())
                .withImagePath(updatedFilm.getImagePath())
                .build();
    }
}
