package com.epam.web.dao;

import java.util.List;
import java.util.Optional;

/**
 * Dao is a parent interface for all dao classes, which provides only
 * basic functionality
 * @param <T> type of specific entity for which dao operations are executed
 * @author Andrey Kuksa
 */

public interface Dao <T>{
    /**
     * Loads all records for according entity from storage
     *
     * @return List which contains all objects for according entity,
     * one object respond to one record in storage
     *
     * @throws DaoException in case of  corrupted request to storage
     */

    List<T>getAll() throws DaoException;

    /**
     * Search a record from storage for chosen id
     *
     * @param id id of searched record
     * @return instance of according entity wrapped in {@link Optional} class if storage
     * contains record with specified id; {@code Optional.empty()} if there is no record
     * with specified id
     * @throws DaoException in case of  corrupted request to storage
     */
    Optional<T> getById(long id) throws DaoException;

    /**
     * Deletes record from storage for specified id.
     *  If it's no record with specified id in the storage, nothing happens.
     *
     * @param id id of record, needed to be deleted
     * @throws DaoException in case of  corrupted request to storage
     */

    void removeById(long id) throws DaoException;

}
