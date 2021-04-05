package com.epam.web.dao.factory;

import com.epam.web.connection.ConnectionPool;
import com.epam.web.dao.DaoException;
import com.epam.web.dao.DaoHelper;

public class DaoHelperFactory implements DaoFactory {

    public DaoHelper create() throws DaoException {
        return new DaoHelper(ConnectionPool.getInstance());
    }

}
