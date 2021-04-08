package com.epam.web.service;

import com.epam.web.dao.DaoException;
import com.epam.web.dao.GenreDao;
import com.epam.web.entity.Genre;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.testng.Assert.*;

public class GenreServiceTest extends ServiceTest{
    private GenreService genreService;

    @Override
    @BeforeMethod
    public void setUp() throws DaoException, ServiceException {
        super.setUp();
        GenreDao genreDaoMock = Mockito.mock(GenreDao.class);
        when(genreDaoMock.getAll()).thenReturn(Collections.emptyList());
        when(daoHelper.createGenreDao()).thenReturn(genreDaoMock);
        this.genreService  = new GenreService(factory);
    }

    @Test
    public void testGetAllGenresShouldReturnAllGenres() throws ServiceException {
        List<Genre> allGenres = genreService.getAllGenres();
        Assert.assertEquals(allGenres, Collections.emptyList());
    }
}