package com.epam.web.command;

import com.epam.web.command.impl.Commands;
import com.epam.web.command.impl.redirect.LoginCommand;
import com.epam.web.entity.Role;
import com.epam.web.entity.User;
import com.epam.web.service.ServiceException;
import com.epam.web.service.UserService;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Optional;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

public class LoginCommandTest extends CommandTest {
    private static final String VALID_USERNAME = "admin";
    private static final String VALID_PASSWORD = "admin";
    private static final User VALID_USER = User.unblocked(0, VALID_USERNAME, 0, Role.ADMIN);
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final CommandResult SUCCESS =
            CommandResult.redirect(Commands.FILMS_PAGE.getName() + "&pageNumber=0");
    private static final CommandResult FAIL = CommandResult
            .redirect(Commands.LOGIN_PAGE_COMMAND.getName() + "&errorMessage=local.loginError");
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
