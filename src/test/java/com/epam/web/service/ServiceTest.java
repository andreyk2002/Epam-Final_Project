package com.epam.web.service;

import com.epam.web.dao.DaoException;
import com.epam.web.dao.DaoHelper;
import com.epam.web.dao.factory.DaoHelperFactory;
import org.mockito.Mockito;
import org.testng.annotations.BeforeMethod;

import static org.mockito.Mockito.when;

public abstract class ServiceTest  {
    protected DaoHelperFactory factory;
    protected DaoHelper daoHelper;

    @BeforeMethod
    public void setUp() throws DaoException, ServiceException {
        daoHelper = Mockito.mock(DaoHelper.class);
        factory = Mockito.mock(DaoHelperFactory.class);
        when(factory.create()).thenReturn(daoHelper);

    }
}
