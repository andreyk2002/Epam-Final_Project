package com.epam.web.command;

import com.epam.web.entity.Role;
import com.epam.web.entity.User;
import com.epam.web.service.ServiceException;
import com.epam.web.service.UserService;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public class LoginCommandTest extends  CommandTest{
    private static final String VALID_USERNAME = "admin";
    private static final String VALID_PASSWORD = "admin";
    private static final String PAGE_NAME = "WEB-INF/view/main.jsp";
    private static final User VALID_USER = new User(0, VALID_USERNAME, 0, Role.ADMIN);
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final CommandResult SUCCESS = CommandResult.redirect("/controller?commandName=showFilmsPage&pageNumber=0");
    private static final CommandResult FAIL = CommandResult.redirect("/controller?commandName=loginPage");
    private LoginCommand command;


    @BeforeMethod
    public void setUp() throws ServiceException {
        super.setUp();
        UserService serviceMock = Mockito.mock(UserService.class);
        when(serviceMock.login(anyString(), anyString())).thenReturn(Optional.empty());
        when(serviceMock.login(VALID_USERNAME, VALID_PASSWORD)).thenReturn(Optional.of(VALID_USER));

        command = new LoginCommand(serviceMock);
    }

    @Test
    public void testExecuteShouldAllowLoginWhenDataIsValid() throws ServiceException {
        when(requestMock.getParameter(USERNAME)).thenReturn(VALID_USERNAME);
        when(requestMock.getParameter(PASSWORD)).thenReturn(VALID_PASSWORD);

        CommandResult result = command.execute(requestMock, responseMock);

        Assert.assertEquals(result, SUCCESS);

    }
    @Test
    public void testExecuteShouldNotAllowLoginWhenDataWrong() throws ServiceException {
        when(requestMock.getParameter(USERNAME)).thenReturn("");
        when(requestMock.getParameter(PASSWORD)).thenReturn("");

        CommandResult result = command.execute(requestMock, responseMock);

        Assert.assertEquals(result, FAIL);
    }
}
