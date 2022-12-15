package by.pertsev.hotel.hiber.dao;

import by.pertsev.hotel.hiber.model.HotelEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * The base interface in the DAO layer.
 *
 * @param <T>
 */
public interface HotelEntityDAO<T extends HotelEntity> {

    /**
     * Adds the new entity.
     *
     * @param entity
     * @return boolean true if the new user has been added, else returns false.
     */
    T save(T entity);

    /**
     * Finds all entities from the table.
     *
     * @return List of the entities.
     */
    Page<T> findAll(Pageable pageable);

    /**
     * Finds entity by its identifier.
     *
     * @param id entity identifier.
     * @return entity with corresponding id.
     */
    T findById(Integer id);

    /**
     * Updates the new entity.
     *
     * @param entity
     * @return boolean true if the new user has been added, else returns false.
     */
    void update(T entity, Integer id);

    /**
     * Deletes the  entity by id.
     *
     * @param id
     * @return boolean true if the new user has been added, else returns false.
     */
    void deleteById(int id);
}


