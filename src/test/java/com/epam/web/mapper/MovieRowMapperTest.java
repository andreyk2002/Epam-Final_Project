package com.epam.web.mapper;

import com.epam.web.dao.DaoException;
import com.epam.web.entity.Movie;
import com.epam.web.entity.Rating;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.mockito.Mockito.when;
import static org.testng.Assert.*;

public class MovieRowMapperTest {
    private static final String ID = "ID";
    private static final String NAME = "Name";
    private static final String IMAGE_PATH = "ImagePath";
    private static final String DESCRIPTION = "Description";
    private static final String GENRE_ID = "GenreID";


    private static final long TEST_ID = 1;
    private static final String TEST_NAME = "admin";
    private static final long TEST_GENRE_ID = 1;
    private final MovieRowMapper mapper = new MovieRowMapper();

    @Test
    public void testMapShouldMapValidUser() throws SQLException, DaoException {
        ResultSet resultSet = Mockito.mock(ResultSet.class);
        when(resultSet.getLong(ID)).thenReturn(TEST_ID);
        when(resultSet.getString(NAME)).thenReturn(TEST_NAME);
        when(resultSet.getString(IMAGE_PATH)).thenReturn("");
        when(resultSet.getString(DESCRIPTION)).thenReturn("");
        when(resultSet.getLong(GENRE_ID)).thenReturn(TEST_GENRE_ID);

        Movie expected = new Movie.Builder(TEST_ID, TEST_NAME, TEST_GENRE_ID).build();

        Movie result = mapper.map(resultSet);

        Assert.assertEquals(expected, result);
    }
}