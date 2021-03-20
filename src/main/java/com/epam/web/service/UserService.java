package com.epam.web.service;

import com.epam.web.dao.DaoException;
import com.epam.web.dao.UserDao;
import com.epam.web.entity.User;

import java.util.Optional;

public class UserService {

    private final UserDao dao;

    public UserService(UserDao dao){
        this.dao = dao;
    }


    public Optional<User> login(String username, String password) throws ServiceException {
        try{
            return dao.findUserByNameAndPassword(username, password);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
