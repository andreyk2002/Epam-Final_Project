package com.epam.web.service;

import com.epam.web.dao.DaoException;
import com.epam.web.dao.DaoHelper;
import com.epam.web.dao.UserDao;
import com.epam.web.dao.factory.DaoHelperFactory;
import com.epam.web.entity.User;
import com.epam.web.validator.UserRatingValidator;

import java.util.List;
import java.util.Optional;

public class UserService {

    private final UserDao userDao;
    private final UserRatingValidator validator;

    public UserService(DaoHelperFactory daoHelperFactory, UserRatingValidator validator) throws ServiceException {
        this.validator = validator;

        try (DaoHelper helper = daoHelperFactory.create()) {
            this.userDao = helper.createUserDao();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public Optional<User> login(String username, String password) throws ServiceException {
        try {
            return userDao.getUserByLoginAndPassword(username, password);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public Optional<User> getUserById(long userId) throws ServiceException {
        try {
            return userDao.getById(userId);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public List<User> getAllUsers() throws ServiceException {
        try {
            return userDao.getAll();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public boolean changeRating(long userId, double newRating) throws ServiceException {
        try {
            if(!validator.validateRating(newRating)){
                return false;
            }
            userDao.changeRating(userId, newRating);
            return true;
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public void changeStatus(long userId, boolean currentStatus) throws ServiceException {
        boolean newStatus = !currentStatus;
        try {
            userDao.changeStatus(userId, newStatus);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
