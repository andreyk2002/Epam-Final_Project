package com.epam.web.mapper;

import com.epam.web.dao.DaoException;
import com.epam.web.entity.Genre;
import com.epam.web.entity.Rating;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.mockito.Mockito.when;
import static org.testng.Assert.*;

public class GenreMapperTest {
    private static final String GENRE_NAME = "name";
    private static final String ID = "ID";

    private static final String TEST_NAME = "comedy";
    private static final long TEST_ID = 1;
    private final GenreMapper mapper = new GenreMapper();

    @Test
    public void testMapShouldMapValidGenre() throws SQLException {
        ResultSet resultSet = Mockito.mock(ResultSet.class);
        when(resultSet.getLong(ID)).thenReturn(TEST_ID);
        when(resultSet.getString(GENRE_NAME)).thenReturn(TEST_NAME);

        Genre expected = new Genre(TEST_ID, TEST_NAME);
        Genre result = mapper.map(resultSet);
        Assert.assertEquals(expected, result);
    }

}