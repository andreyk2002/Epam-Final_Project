package com.epam.web.command;

import com.epam.web.entity.User;
import com.epam.web.service.UserService;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

public class LoginCommandTest {
    private static final String VALID_USERNAME = "admin";
    private static final String VALID_PASSWORD = "admin";
    private static final String PAGE_NAME = "WEB-INF/view/main.jsp";


    @Test
    public void testExecuteShouldReturnValidPage(){
        UserService service = Mockito.mock(UserService.class);
        User user = new User(VALID_USERNAME);
        when(service.login(anyString(), anyString())).thenReturn(Optional.of(user));

        Command command = new LoginCommand(service);
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        String fileName = command.execute(request, response);
        Assert.assertEquals(fileName, PAGE_NAME);

    }

}
