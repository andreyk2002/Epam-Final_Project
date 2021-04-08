package com.epam.web.mapper;

import com.epam.web.dao.DaoException;
import com.epam.web.entity.Rating;
import com.epam.web.entity.Review;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.mockito.Mockito.when;

public class RatingMapperTest {

    private static final String RATING = "Rating";
    private static final String ID = "UserID";
    private static final String FILM_ID = "FilmID";

    private static final long TEST_ID = 1;
    private static final long TEST_FILM_ID = 1;
    private static final int TEST_RATING = 4;
    private final RatingMapper mapper = new RatingMapper();

    @Test
    public void testMapShouldMapValidUser() throws SQLException, DaoException {
        ResultSet resultSet = Mockito.mock(ResultSet.class);
        when(resultSet.getLong(ID)).thenReturn(TEST_ID);
        when(resultSet.getLong(FILM_ID)).thenReturn(TEST_FILM_ID);
        when(resultSet.getInt(RATING)).thenReturn(TEST_RATING);

        Rating expected = new Rating(TEST_RATING, TEST_ID, TEST_FILM_ID);
        Rating result = mapper.map(resultSet);

        Assert.assertEquals(expected, result);
    }
}