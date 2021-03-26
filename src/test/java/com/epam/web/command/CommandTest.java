package com.epam.web.command;

import com.epam.web.service.ServiceException;
import org.mockito.Mockito;
import org.testng.annotations.BeforeMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.testng.Assert.*;

abstract class CommandTest {
    protected HttpServletRequest requestMock;
    protected HttpServletResponse responseMock;

    @BeforeMethod
    public void setUp() throws ServiceException {
        responseMock = Mockito.mock(HttpServletResponse.class);
        requestMock = Mockito.mock(HttpServletRequest.class);
        HttpSession sessionMock = Mockito.mock(HttpSession.class);
        doNothing().when(sessionMock).setAttribute(anyString(), anyString());
        when(requestMock.getSession()).thenReturn(sessionMock);
        when(requestMock.getContextPath()).thenReturn("");
    }

}