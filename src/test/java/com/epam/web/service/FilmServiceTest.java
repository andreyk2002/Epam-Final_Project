package com.epam.web.service;

import com.epam.web.dao.*;
import com.epam.web.dto.MovieDTO;
import com.epam.web.entity.Genre;
import com.epam.web.entity.Movie;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;

public class FilmServiceTest extends ServiceTest{

    public static final long VALID_ID = 0;
    private static final Genre VALID_GENRE = new Genre(VALID_ID, "");
    private static final Double VALID_RATING = 5.;
    private static final long INVALID_ID = -1L;
    private static final int VALID_PAGE = 0;
    private static final int INVALID_PAGE = -1;
    private static final Movie VALID_MOVIE = new Movie.Builder(VALID_ID, "", 0).build();
    private static final MovieDTO VALID_DTO =
            new MovieDTO(VALID_MOVIE, VALID_GENRE.getName() , VALID_RATING, Collections.emptyList());

    private FilmService service;
    @Override
    @BeforeMethod
    public void setUp() throws DaoException, ServiceException {
        super.setUp();
        FilmDao filmDaoMock = Mockito.mock(FilmDao.class);
        GenreDao genreDaoMock = Mockito.mock(GenreDao.class);
        RatingDao ratingDaoMock = Mockito.mock(RatingDao.class);
        ReviewDao reviewDaoMock = Mockito.mock(ReviewDao.class);

        when(genreDaoMock.getById(anyLong())).thenReturn(Optional.of(VALID_GENRE));
        when(ratingDaoMock.getMovieRating(anyLong())).thenReturn(VALID_RATING);
        when(filmDaoMock.getById(VALID_ID)).thenReturn(Optional.of(VALID_MOVIE));
        when(filmDaoMock.getById(INVALID_ID)).thenReturn((Optional.empty()));
        when(filmDaoMock.getMoviesForPage(VALID_PAGE)).thenReturn(Collections.singletonList(VALID_MOVIE));
        when(filmDaoMock.getMoviesForPage(INVALID_PAGE)).thenReturn(Collections.emptyList());
        when(reviewDaoMock.getFilmReviews(anyLong())).thenReturn(Collections.emptyList());


        when(daoHelper.createMovieDao()).thenReturn(filmDaoMock);
        when(daoHelper.createGenreDao()).thenReturn(genreDaoMock);
        when(daoHelper.createRatingDao()).thenReturn(ratingDaoMock);
        when(daoHelper.createReviewDao()).thenReturn(reviewDaoMock);

        service = new FilmService(factory);
    }

    @Test
    public void testGetMovieByIdShouldReturnMovieWhenMovieExist() throws DaoException {
        Optional<MovieDTO> movieById = service.getMovieById(VALID_ID);
        Assert.assertEquals(movieById.get(), VALID_DTO);
    }

    @Test
    public void testGetMovieByIdShouldReturnEmptyWhenMovieNotExist() throws DaoException {
        Optional<MovieDTO> movieById = service.getMovieById(INVALID_ID);
        Assert.assertEquals(movieById, Optional.empty());
    }

    @Test
    public void testGetFilmsPageShouldReturnFilmsForValidPage() throws ServiceException {
        List<MovieDTO>expected = Collections.singletonList(VALID_DTO);
        List<MovieDTO> nextMovies = service.getNextMovies(VALID_PAGE);
        Assert.assertEquals(nextMovies, expected);
    }

    @Test
    public void testGetFilmsPageShouldReturnEmptyListForInvalidPage() throws ServiceException {
        List<MovieDTO> nextMovies = service.getNextMovies(INVALID_PAGE);
        Assert.assertEquals(nextMovies, Collections.emptyList());
    }
}