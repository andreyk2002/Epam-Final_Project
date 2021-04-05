package com.epam.web.dao;

import java.util.List;
import java.util.Optional;

public interface Dao <T>{

    List<T>getAll() throws DaoException;

    Optional<T> getById(long id) throws DaoException;

    void removeById(long id) throws Exception;

}
