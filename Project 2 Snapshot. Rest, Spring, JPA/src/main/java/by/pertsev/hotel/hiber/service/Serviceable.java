package by.pertsev.hotel.hiber.service;

import by.pertsev.hotel.hiber.model.HotelEntity;
import by.pertsev.hotel.hiber.model.dto.HotelDTOEntity;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface Serviceable<E extends HotelEntity, D extends HotelDTOEntity> {

    /**
     * Adds the new entity.
     *
     * @param dto
     * @return boolean true if the new entity has been added, else returns false.
     */
    E save(D dto);

    /**
     * Find all entities for specified page.
     *
     * @param pageable the pageable to request a paged result, can be Pageable.unpaged(), must not be null.
     * @return the page with entities
     * <p>
     * // * @throws NotFoundException if page with offers doesn't exist
     */
    Page<E> findAll(Pageable pageable);

    /**
     * Find entity by its identifier.
     *
     * @param id
     * @return entity with corresponding id.
     */
    E findById(int id);

    /**
     * Updates entity.
     *
     * @param dto the entitywith new data
     * @param id  the id of updated entity
     */

    //      @return boolean true if the entity has been updated, else returns false.
    void update(D dto, Integer id);

    /**
     * Delete entity with specified id.
     *
     * @param id the entity id
     * @throws EmptyResultDataAccessException if entity wasn't found  //TODO EXC
     */
    void deleteById(int id);

    /**
     * Find offers for specified page according to specified parameters.
     *
     * @param params the parameters determinant search for offers
     * @param pageable the pageable to request a paged result, can be Pageable.unpaged(), must not be null.
     * @return the list with offers according to specified parameters
    //  * @throws ResourceNotFoundException if page with offers doesn't exist
     */
    //  Page<Offer> findOffersParams(OfferFilter params, Pageable pageable);


}