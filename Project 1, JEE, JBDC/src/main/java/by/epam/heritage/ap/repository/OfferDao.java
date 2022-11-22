package by.epam.heritage.ap.repository;

import by.epam.heritage.ap.model.Offer;

import java.util.List;

public interface OfferDao extends BaseDAO<Offer> {
    /**
     * Finds all offers of the User.
     *
     * @param idGuest - guest ID.
     * @return List of offers
     * @throws DAOException
     */
    List<Offer> getOffersByGuestId(int idGuest) throws DAOException;

    /**
     * Updates Offer data.
     *
     * @param entity
     * @return boolean true if the Offer has been updated, else returns false.
     * @throws DAOException
     */
    boolean update(Offer entity) throws DAOException;

    /**
     * Adds new Offer.
     *
     * @param entity
     * @return boolean true if the new Offer has been added, else returns false.
     * @throws DAOException
     */
    boolean insert(Offer entity) throws DAOException;


}

