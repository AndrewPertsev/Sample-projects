package by.epam.heritage.ap.service;

import by.epam.heritage.ap.model.Offer;

import java.math.BigDecimal;
import java.util.List;

public interface OfferServiceable extends Serviceable<Offer> {
    /**
     * Assembles offer using request params witn certain apartment.
     *
     * @param suitableApartmentId - suitable apartment ID.
     * @param requestId           - user's request ID.
     * @return Offer with price for certain user's request.
     * @throws ServiceException
     */
    Offer assembleOfferForApartment(int suitableApartmentId, int requestId) throws ServiceException;

    /**
     * Calculates total offer price, by request id.
     *
     * @param requestId - user's request ID.
     * @return price, BigDecimal format.
     * @throws ServiceException
     */
    BigDecimal calculateOfferTotalPrice(int requestId) throws ServiceException;

    /**
     * Finds all offers of the User.
     *
     * @param idGuest - guest ID.
     * @return List of offers
     * @throws ServiceException
     */
    List<Offer> getOffersByGuestId(int idGuest) throws ServiceException;

}
