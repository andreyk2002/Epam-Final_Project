package com.epam.web.service;

import com.epam.web.dao.DaoException;
import com.epam.web.dao.UserDao;
import com.epam.web.entity.Role;
import com.epam.web.entity.User;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.testng.Assert.*;

public class UserServiceTest extends ServiceTest{

    public static final int VALID_ID = 0;
    public static final User VALID_USER = User.unblocked(VALID_ID, "admin", 50, Role.ADMIN);
    public static final String VALID_LOGIN = "admin";
    public static final String VALID_PASSWORD = "admin";
    public static final String INVALID_LOGIN = "";
    public static final String INVALID_PASSWORD = "1234";
    private static final long INVALID_ID = -1;
    private UserService service;

    @Override
    @BeforeMethod
    public void setUp() throws com.epam.web.dao.DaoException, ServiceException {
        super.setUp();
        UserDao userDaoMock = Mockito.mock(UserDao.class);
        when(userDaoMock.getAll()).thenReturn(Collections.singletonList(VALID_USER));
        when(userDaoMock.getUserByLoginAndPassword(VALID_LOGIN, VALID_PASSWORD)).thenReturn(Optional.of(VALID_USER));
        when(userDaoMock.getUserByLoginAndPassword(INVALID_LOGIN, INVALID_PASSWORD)).thenReturn(Optional.empty());
        when(userDaoMock.getById(VALID_ID)).thenReturn(Optional.of(VALID_USER));
        when(userDaoMock.getById(INVALID_ID)).thenReturn(Optional.empty());
        when(daoHelper.createUserDao()).thenReturn(userDaoMock);
        service = new UserService(factory);
    }


    @Test
    public void testGetUserByIdShouldReturnUserWhenUserExist() throws ServiceException {
        Optional<User> movieById = service.getUserById(VALID_ID);
        Assert.assertEquals(movieById.get(), VALID_USER);
    }

    @Test
    public void testGetUserByIdShouldReturnEmptyWhenUserNotExist() throws ServiceException {
        Optional<User> movieById = service.getUserById(INVALID_ID);
        Assert.assertEquals(movieById, Optional.empty());
    }

    @Test
    public void testGetUserByLoginAndPasswordShouldReturnUserWhenDataValid() throws ServiceException{
        Optional<User> optionalUser = service.login(VALID_LOGIN, VALID_PASSWORD);
        Assert.assertEquals(VALID_USER, optionalUser.get());
    }

    @Test
    public void testGetUserByLoginAndPasswordShouldReturnEmptyWhenDataInvalid() throws ServiceException{
        Optional<User> optionalUser = service.login(INVALID_LOGIN, INVALID_PASSWORD);
        Assert.assertEquals(Optional.empty(), optionalUser);
    }

    @Test
    public void testGetAllUsersShouldReturnAllUsers() throws ServiceException{
        List<User> allUsers = service.getAllUsers();
        Assert.assertEquals(allUsers, Collections.singletonList(VALID_USER));
    }

    
}