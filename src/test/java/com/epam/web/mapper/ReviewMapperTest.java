package com.epam.web.mapper;

import com.epam.web.dao.DaoException;
import com.epam.web.entity.Rating;
import com.epam.web.entity.Review;
import com.epam.web.entity.Role;
import com.epam.web.entity.User;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.mockito.Mockito.when;

public class ReviewMapperTest {
    private static final String REVIEW = "review";
    private static final String ID = "ID";
    private static final String USERNAME = "username";

    private static final long TEST_ID = 1;
    private static final String TEST_USERNAME = "user";
    private static final String TEST_REVIEW = "";
    private final ReviewMapper mapper = new ReviewMapper();

    @Test
    public void testMapShouldMapValidUser() throws SQLException, DaoException {
        ResultSet resultSet = Mockito.mock(ResultSet.class);
        when(resultSet.getLong(ID)).thenReturn(TEST_ID);
        when(resultSet.getString(USERNAME)).thenReturn(TEST_USERNAME);
        when(resultSet.getString(REVIEW)).thenReturn(TEST_REVIEW);

        Review expected = new Review(TEST_USERNAME, TEST_REVIEW, TEST_ID);
        Review result = mapper.map(resultSet);

        Assert.assertEquals(expected, result);
    }

}