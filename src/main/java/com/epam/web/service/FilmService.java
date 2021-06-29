package com.epam.web.service;

import com.epam.web.dao.*;
import com.epam.web.dao.factory.DaoHelperFactory;
import com.epam.web.dto.FilmDto;
import com.epam.web.entity.Film;
import com.epam.web.entity.Genre;
import com.epam.web.entity.Review;
import com.epam.web.security.XssProtector;
import com.epam.web.service.rating.FilmObserver;
import org.decimal4j.util.DoubleRounder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FilmService {

    public static final int ROUND_PRECISION = 2;
    private final XssProtector protect;
    private final DaoHelperFactory factory;
    private final FilmObserver filmObserver;

    public FilmService(DaoHelperFactory factory, XssProtector protect, FilmObserver filmObserver) throws ServiceException {
        this.factory = factory;
        this.protect = protect;
        this.filmObserver = filmObserver;
    }

    public List<FilmDto> getPage(int pageNumb, long userId) throws ServiceException {
        try (DaoHelper helper = factory.create()) {
            FilmDao filmDao = helper.createFilmDao();
            List<Film> moviesForPage = filmDao.getMoviesForPage(pageNumb);
            return getFilmsDto(moviesForPage, userId);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public List<FilmDto> getByName(String filmName, long userId) throws ServiceException {
        try (DaoHelper helper = factory.create()) {
            FilmDao filmDao = helper.createFilmDao();
            List<Film> moviesByName = filmDao.getFilmsByName(filmName);
            return getFilmsDto(moviesByName, userId);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public List<FilmDto> getByGenreId(long genreId, long userId) throws ServiceException {
        try (DaoHelper helper = factory.create()) {
            FilmDao filmDao = helper.createFilmDao();
            List<Film> filmsByGenre = filmDao.getByGenreId(genreId);
            return getFilmsDto(filmsByGenre, userId);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    private List<FilmDto> getFilmsDto(List<Film> moviesForPage, long userId) throws ServiceException {
        List<FilmDto> filmsDto = new ArrayList<>();
        for (Film film : moviesForPage) {
            FilmDto filmDTO = getMovieDTO(film, userId);
            filmsDto.add(filmDTO);
        }
        return filmsDto;
    }

    public int getPagesCount() throws ServiceException {
        try (DaoHelper helper = factory.create()) {
            FilmDao filmDao = helper.createFilmDao();
            return filmDao.getPagesCount();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public Optional<FilmDto> getFilmDtoById(long id, long userId) throws ServiceException {
        try (DaoHelper helper = factory.create()) {
            FilmDao filmDao = helper.createFilmDao();
            Optional<Film> optionalMovie = filmDao.getById(id);
            if (optionalMovie.isPresent()) {
                Film film = optionalMovie.get();
                FilmDto filmDTO = getMovieDTO(film, userId);
                return Optional.of(filmDTO);
            }
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return Optional.empty();
    }


    private FilmDto getMovieDTO(Film film, long userId) throws ServiceException {
        try (DaoHelper helper = factory.create()) {
            GenreDao genreDao = helper.createGenreDao();
            RatingDao ratingDao = helper.createRatingDao();
            ReviewDao reviewDao = helper.createReviewDao();
            List<Genre> filmsGenres = genreDao.getByFilm(film.getId());
            long movieId = film.getId();
            double movieRating = ratingDao.getMovieRating(movieId);
            double ratingRounded = DoubleRounder.round(movieRating, ROUND_PRECISION);
            List<Review> filmReviews = reviewDao.getFilmReviews(movieId);
            boolean isRatedByUser = ratingDao.hasRating(movieId, userId);
            return new FilmDto.Builder()
                    .withFilm(film)
                    .withGenres(filmsGenres)
                    .withRating(ratingRounded)
                    .withReviews(filmReviews)
                    .withUserRated(isRatedByUser)
                    .build();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public void saveFilm(Film film, List<Long> genresId) throws ServiceException {
        Film safeFilm = removeMalformedData(film);
        try (DaoHelper daoHelper = factory.create()) {
            daoHelper.startTransaction();
            FilmDao filmDao = daoHelper.createFilmDao();
            FilmGenreDao filmGenreDao = daoHelper.createFilmGenreDao();
            long filmId = filmDao.saveAndGetID(safeFilm);
            filmGenreDao.addFilmsGenres(filmId, genresId);
            daoHelper.endTransaction();
            filmObserver.notifyFilmAdded(filmId);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public void removeById(long filmId) throws ServiceException {
        try (DaoHelper daoHelper = factory.create()) {
            FilmDao filmDao = daoHelper.createFilmDao();
            RatingDao ratingDao = daoHelper.createRatingDao();
            ReviewDao reviewDao = daoHelper.createReviewDao();
            FilmGenreDao filmGenreDao = daoHelper.createFilmGenreDao();
            filmGenreDao.removeByFilmId(filmId);
            reviewDao.removeFilmsReviews(filmId);
            ratingDao.removeFilmsRatings(filmId);
            filmDao.removeById(filmId);
            filmObserver.notifyFilmDeleted(filmId);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public Optional<Film> getFilmById(long filmId) throws ServiceException {
        try (DaoHelper daoHelper = factory.create()) {
            FilmDao filmDao = daoHelper.createFilmDao();
            return filmDao.getById(filmId);
        }catch (DaoException e){
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public void updateFilm(Film updatedFilm, List<Long> newGenres) throws ServiceException {
        long id = updatedFilm.getId();
        try (DaoHelper daoHelper = factory.create()) {
            daoHelper.startTransaction();
            FilmDao filmDao = daoHelper.createFilmDao();
            FilmGenreDao filmGenreDao = daoHelper.createFilmGenreDao();
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
