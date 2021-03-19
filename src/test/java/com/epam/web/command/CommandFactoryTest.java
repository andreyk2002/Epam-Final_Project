package com.epam.web.command;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CommandFactoryTest {

    private static final String SHOW_PAGE = "show-page";
    private static final String LOGIN_COMMAND = "login";
    private final CommandFactory factory = new CommandFactory();

    @Test
    public void testCreateShouldReturnLoginCommandWhenLogin() throws CommandNotExistException {
        Command result = factory.create(LOGIN_COMMAND);
        Assert.assertEquals(result.getClass(), LoginCommand.class);
    }

    @Test
    public void testCreateShouldReturnRegisterCommandWhenLogin() throws CommandNotExistException {
        Command result = factory.create(SHOW_PAGE);
        Assert.assertEquals(result.getClass(), ShowPageCommand.class);
    }

    @Test(expectedExceptions = CommandNotExistException.class)
    public void testCreateShouldThrowExceptionWhenCommandNameIsWrong() throws CommandNotExistException {
        Command result = factory.create("wrong_input");
    }
}