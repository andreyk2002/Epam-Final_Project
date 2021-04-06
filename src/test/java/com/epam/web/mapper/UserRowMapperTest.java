package com.epam.web.mapper;

import com.epam.web.entity.Role;
import com.epam.web.entity.User;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.mockito.Mockito.when;

public class UserRowMapperTest {

    private final UserRowMapper mapper = new UserRowMapper();

    private static final String BLOCKED = "Blocked";
    private static final String ID = "ID";
    private static final String LOGIN = "login";
    private static final String ROLE = "role";
    private static final String RATING = "rating";

    private static final long TEST_ID = 1;
    private static final String TEST_LOGIN = "login";
    private static final String TEST_ROLE = "user";
    private static final int TEST_RATING = 50;

    @Test
    public void testMapShouldMapValidUser() throws SQLException {
        ResultSet resultSet = Mockito.mock(ResultSet.class);
        when(resultSet.getLong(ID)).thenReturn(TEST_ID);
        when(resultSet.getString(TEST_LOGIN)).thenReturn(TEST_LOGIN);
        when(resultSet.getString(ROLE)).thenReturn(TEST_ROLE);
        when(resultSet.getInt(RATING)).thenReturn(TEST_RATING);
        when(resultSet.getInt(BLOCKED)).thenReturn(TEST_RATING);
        User expected = User.unblocked(TEST_ID, TEST_LOGIN, TEST_RATING, Role.USER);

        User actual = mapper.map(resultSet);

        Assert.assertEquals(expected, actual);
    }


}