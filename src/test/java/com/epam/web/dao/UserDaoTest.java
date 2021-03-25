package com.epam.web.dao;


import com.epam.web.entity.User;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Optional;

public class UserDaoTest {

    private static final String VALID_USERNAME = "admin";
    private static final String VALID_PASSWORD = "admin";
    private static final String INVALID_USERNAME = "234@";

    private final WUserDao dao = new WUserDao();

    @SuppressWarnings("OptionalGetWithoutIsPresent")
//    @Test
//    public void testFindUserByLoginAndPasswordShouldFindWhenPasswordIsValid() throws DaoException {
//        User expectedUser = new User(1, "admin");
//        Optional<User>user = dao.findUserByNameAndPassword(VALID_USERNAME, VALID_PASSWORD);
//        User result = user.get();
//        Assert.assertEquals(expectedUser, result);
//    }

    @Test
    public void testFindUserByLoginAndPasswordShouldReturnEmptyWhenDataInvalid() throws DaoException {
        Optional<User> user = dao.findUserByNameAndPassword(INVALID_USERNAME, VALID_PASSWORD);
        Assert.assertFalse(user.isPresent());
    }
}