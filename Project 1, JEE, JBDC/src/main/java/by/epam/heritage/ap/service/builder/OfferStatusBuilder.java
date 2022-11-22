package by.epam.heritage.ap.service.builder;

import by.epam.heritage.ap.model.Offer;
import by.epam.heritage.ap.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

import static by.epam.heritage.ap.controller.ConstantsParametersAndAttributes.*;

public class OfferStatusBuilder implements Buildable {
    private static final Logger logger = LogManager.getLogger(OfferStatusBuilder.class);

    @Override
    public Offer create(HttpServletRequest request) throws ServiceException {

        Offer offer = new Offer();

        int offerId = Integer.parseInt(request.getParameter(PARAMETER_OFFER_ID).trim());
        Boolean isSent = Boolean.parseBoolean(request.getParameter(PARAMETER_OFFER_SENT));
        Boolean isPaid = Boolean.parseBoolean(request.getParameter(PARAMETER_OFFER_PAID));
        Boolean isClosed = Boolean.parseBoolean(request.getParameter(PARAMETER_OFFER_CLOSED));
        BigDecimal priceOffer = BigDecimal.valueOf(Long.parseLong(request.getParameter(PARAMETER_OFFER_PRICE)));

        offer.setSent(isSent);
        offer.setPaid(isPaid);
        offer.setClosed(isClosed);
        offer.setOfferId(offerId);
        offer.setPriceOffer(priceOffer);
        logger.debug("Offer status builder create");

        return offer;
    }


}
