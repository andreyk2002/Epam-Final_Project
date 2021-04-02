package com.epam.web.service;

import com.epam.web.dao.DaoHelper;
import com.epam.web.dao.impl.UserDaoImpl;
import com.epam.web.dao.factory.DaoHelperFactory;
import com.epam.web.entity.User;

import java.util.Optional;

public class UserService {

    private final DaoHelperFactory daoHelperFactory;

    public UserService(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    public Optional<User> login(String username, String password) throws ServiceException {
        try(DaoHelper helper = daoHelperFactory.create()){
            helper.startTransaction();
            UserDaoImpl dao = helper.createUserDao();
            Optional<User> user = dao.getUserByLoginAndPassword(username, password);
            helper.endTransaction();
            return user;
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
