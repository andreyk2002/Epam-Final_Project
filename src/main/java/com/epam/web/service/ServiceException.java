package com.epam.web.service;

import com.epam.web.dao.DaoException;

public class ServiceException extends Exception {
    public ServiceException(String message, DaoException e) {
        super(message, e);
    }
}
