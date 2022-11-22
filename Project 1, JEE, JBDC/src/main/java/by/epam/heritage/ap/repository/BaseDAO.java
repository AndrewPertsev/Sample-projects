package by.epam.heritage.ap.repository;

import by.epam.heritage.ap.model.HotelEntity;

import java.util.List;

/**
 * The base interface in the DAO layer.
 *
 * @param <T>
 */
public interface BaseDAO<T extends HotelEntity> {

    /**
     * Find all entities from the table.
     *
     * @return List of the entities.
     * @throws DAOException
     */
    List<T> findAll() throws DAOException;

    /**
     * Find entity by its identifier.
     *
     * @param id entity identifier.
     * @return entity with corresponding id.
     * @throws DAOException
     */
    T findById(int id) throws DAOException;

}
