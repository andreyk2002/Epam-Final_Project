package com.epam.web.command;

import com.epam.web.service.MovieService;
import com.epam.web.service.ServiceException;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Collections;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;
import static org.testng.Assert.*;

public class ShowFilmsPageCommandTest extends CommandTest{

    private static final String FIRST_PAGE = "0";
    private static final CommandResult SUCCESS =
            CommandResult.redirect("/controller?commandName=mainPage&pageNumber=" + FIRST_PAGE);
    private static final String PAGE = "pageNumber";
    private MovieService serviceMock;
    private ShowFilmsPageCommand command;



    @BeforeMethod
    public void setUp() throws ServiceException {
        super.setUp();
        when(requestMock.getParameter(PAGE)).thenReturn(FIRST_PAGE);

        serviceMock = Mockito.mock(MovieService.class);
        when(serviceMock.getPagesCount()).thenReturn(1);
        when(serviceMock.getNextMovies(anyInt())).thenReturn(Collections.emptyList());
        command = new ShowFilmsPageCommand(serviceMock);
    }

    @Test
    public void testShouldExecute() throws ServiceException {
        CommandResult result = command.execute(requestMock, responseMock);
        Assert.assertEquals(result, SUCCESS);
    }
}