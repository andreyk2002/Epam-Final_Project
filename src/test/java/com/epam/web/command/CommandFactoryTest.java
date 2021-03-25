package com.epam.web.command;

import com.epam.web.dao.DaoException;
import com.epam.web.service.UserService;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CommandFactoryTest {

    private static final String SHOW_PAGE = "show-page";
    private static final String LOGIN_COMMAND = "login";
    private final CommandFactory factory = new CommandFactory();
    private static final String REGISTER_PAGE = "/WEB-INF/view/register.jsp";
    private static final String MAIN_PAGE = "/WEB-INF/view/main.jsp";
    private static final String USER_MANAGE_PAGE = "/WEB-INF/view/userManage.jsp";
    private static final String PERSONAL_PAGE = "/WEB-INF/view/personal.jsp";
    private static final String FILM_MANAGE_PAGE = "/WEB-INF/view/filmManage.jsp";
    private static final String LOGIN_PAGE = "/index.jsp";


    @Test
    public void testCreateShouldReturnLoginCommandWhenLogin() throws CommandNotExistException, DaoException {
        Command result = factory.create(LOGIN_COMMAND);
        Assert.assertEquals(result.getClass(), LoginCommand.class);
    }



    @Test(expectedExceptions = CommandNotExistException.class)
    public void testCreateShouldThrowExceptionWhenCommandNameIsWrong() throws CommandNotExistException, DaoException {
        Command result = factory.create("wrong_input");
    }


}