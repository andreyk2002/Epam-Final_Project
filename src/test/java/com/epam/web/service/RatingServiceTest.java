package com.epam.web.service;

import com.epam.web.dao.DaoException;
import com.epam.web.dao.RatingDao;
import com.epam.web.dao.UserDao;
import com.epam.web.dao.impl.RatingDaoImpl;
import com.epam.web.dao.impl.UserDaoImpl;
import com.epam.web.service.rating.RatingManager;
import com.epam.web.validator.RatingValidator;
import org.mockito.Mockito;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Matchers.anyDouble;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

public class RatingServiceTest extends ServiceTest {


    @Override
    @BeforeMethod
    public void setUp() throws ServiceException, DaoException {
        super.setUp();
        RatingDao ratingDaoMock = Mockito.mock(RatingDaoImpl.class);
        UserDao userDaoMock = Mockito.mock(UserDaoImpl.class);
        when(daoHelper.createRatingDao()).thenReturn(ratingDaoMock);
        when(daoHelper.createUserDao()).thenReturn(userDaoMock);
    }

    @Test
    public void rateFilmShouldReturnWrongRatingWhenRatingIsOutOfBounds() throws ServiceException {
        RatingValidator validator = Mockito.mock(RatingValidator.class);
        when(validator.validateRating(anyDouble())).thenReturn(false);
        RatingService service = new RatingService(factory, validator);
        RatingManager ratingManagerMock = Mockito.mock(RatingManager.class);
        when(ratingManagerMock.addRating(anyObject())).thenReturn(true);
        RatingStatus ratingStatus = service.rateFilm(ratingManagerMock,1, 1, 7);
        assertEquals(ratingStatus, RatingStatus.WRONG_RATING);

    }

    @Test
    public void rateFilmShouldReturnAlreadyRatedWhenFilmIsAlreadyRated() throws ServiceException {
        RatingValidator validator = Mockito.mock(RatingValidator.class);
        when(validator.validateRating(anyDouble())).thenReturn(true);
        RatingService service = new RatingService(factory, validator);
        RatingManager ratingManagerMock = Mockito.mock(RatingManager.class);
        when(ratingManagerMock.addRating(anyObject())).thenReturn(false);
        RatingStatus ratingStatus = service.rateFilm(ratingManagerMock, 1, 1, 4);
        assertEquals(ratingStatus, RatingStatus.ALREADY_RATED);
    }

    @Test
    public void rateFilmShouldReturnSuccessWhenRatingIsValid() throws ServiceException {
        RatingValidator validator = Mockito.mock(RatingValidator.class);
        when(validator.validateRating(anyDouble())).thenReturn(true);
        RatingService service = new RatingService(factory, validator);
        RatingManager ratingManagerMock = Mockito.mock(RatingManager.class);
        when(ratingManagerMock.addRating(anyObject())).thenReturn(true);
        RatingStatus ratingStatus = service.rateFilm(ratingManagerMock, 1, 1, 4);
        assertEquals(ratingStatus, RatingStatus.SUCCESS);
    }
}