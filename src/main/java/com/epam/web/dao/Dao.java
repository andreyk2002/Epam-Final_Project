package com.epam.web.dao;

import com.epam.web.entity.Identifiable;
import java.util.List;
import java.util.Optional;

public interface Dao <T extends Identifiable>{

    List<T>getAll() throws DaoException;

    Optional<T> getById(long id) throws Exception;

    void add(T item) throws DaoException;

    void removeById(long id) throws DaoException, Exception;

}
