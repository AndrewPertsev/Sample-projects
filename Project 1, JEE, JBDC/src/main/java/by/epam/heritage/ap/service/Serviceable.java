package by.epam.heritage.ap.service;

import by.epam.heritage.ap.model.HotelEntity;

import java.util.List;

public interface Serviceable<T extends HotelEntity> {

    /**
     * Updates entity.
     *
     * @param entity
     * @return boolean true if the entity has been updated, else returns false.
     * @throws ServiceException
     */
    boolean update(T entity) throws ServiceException;

    /**
     * Adds the new user.
     *
     * @param entity
     * @return boolean true if the new user has been added, else returns false.
     * @throws ServiceException
     */
    boolean add(T entity) throws ServiceException;

    /**
     * Find all entities from the table.
     *
     * @return List of the entities.
     * @throws ServiceException
     */
    List<T> findAll() throws ServiceException;

    /**
     * Find entity by its identifier.
     *
     * @param id
     * @return entity with corresponding id.
     * @throws ServiceException
     */
    T findById(int id) throws ServiceException;
}
