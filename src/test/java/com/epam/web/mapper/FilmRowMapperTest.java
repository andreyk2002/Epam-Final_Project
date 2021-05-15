package com.epam.web.mapper;

import com.epam.web.dao.DaoException;
import com.epam.web.entity.Film;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.mockito.Mockito.when;

public class FilmRowMapperTest {
    private static final String ID = "ID";
    private static final String NAME = "Name";
    private static final String IMAGE_PATH = "ImagePath";
    private static final String DESCRIPTION = "Description";
    private static final String GENRE_ID = "GenreID";


    private static final long TEST_ID = 1;
    private static final String TEST_NAME = "admin";
    private static final long TEST_GENRE_ID = 1;
    private final FilmRowMapper mapper = new FilmRowMapper();



    @Test
    public void testMapShouldMapValidUser() throws SQLException {
        ResultSet resultSet = Mockito.mock(ResultSet.class);
        when(resultSet.getLong(ID)).thenReturn(TEST_ID);
        when(resultSet.getString(NAME)).thenReturn(TEST_NAME);
        when(resultSet.getString(IMAGE_PATH)).thenReturn("");
        when(resultSet.getString(DESCRIPTION)).thenReturn("");
        when(resultSet.getLong(GENRE_ID)).thenReturn(TEST_GENRE_ID);

        Film expected = new Film.Builder()
                .withName(TEST_NAME)
                .withGenreId(TEST_GENRE_ID)
                .withId(TEST_ID)
                .build();

        Film result = mapper.map(resultSet);

        Assert.assertEquals(expected, result);
    }
}