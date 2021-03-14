package com.epam.web.service;

import com.epam.web.entity.User;
import org.testng.Assert;
import org.testng.annotations.Test;


import java.util.Optional;

public class UserServiceTest {
    private static final String VALID_LOGIN = "admin";
    private static final String VALID_PASSWORD = "admin";
    private static final String WRONG_LOGIN = "";
    private static final String WRONG_PASSWORD = "";

    private final UserService userService = new UserService();

    @Test
    public void testLoginShouldLoginWhenDataValid(){
        Optional<User> user = userService.login(VALID_LOGIN, VALID_PASSWORD);
        Assert.assertTrue(user.isPresent());
    }

    @Test
    public void testLoginShouldNotLoginWhenDataInvalid(){
        Optional<User> user = userService.login(WRONG_LOGIN, WRONG_PASSWORD);
        Assert.assertFalse(user.isPresent());
    }
}
