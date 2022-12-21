package by.pertsev.hotel.hiber.service;

import by.pertsev.hotel.hiber.model.Offer;
import by.pertsev.hotel.hiber.model.dto.OfferDto;

import java.math.BigDecimal;
import java.util.List;

//@Profile("data")

public interface OfferServiceable extends Serviceable<Offer, OfferDto> {

    /**
     * Assembles offer using request params witn certain apartment.
     *
     * @param suitableApartmentId - suitable apartment ID.
     * @param requestId           - user's request ID.
     * @return Offer with price for certain user's request.
     */
    OfferDto assembleOfferForApartment(int suitableApartmentId, int requestId);

    /**
     * Calculates total offer price, by request id.
     *
     * @param requestId - user's request ID.
     * @return price, BigDecimal format.
     */
    BigDecimal calculateOfferTotalPrice(int requestId);

    /**
     * Finds all offers of the User.
     *
     * @param idGuest - guest ID.
     * @return List of offers
     */
    // Page<Offer> findOffersByGuestId(int idGuest, Pageable pageable);

    /**
     * @param requestId
     * @return
     */
    List<OfferDto> makeAvailablePriceList(int requestId);


}
