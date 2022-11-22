package by.epam.heritage.ap.service.validator;

import by.epam.heritage.ap.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static by.epam.heritage.ap.controller.ConstantsParametersAndAttributes.PARAMETER_OFFER_ID;
import static by.epam.heritage.ap.controller.ConstantsParametersAndAttributes.PARAMETER_OFFER_PRICE;
import static by.epam.heritage.ap.service.validator.ValidatorConstants.*;

public class OfferStatusValidator implements Validable {
    private static final Logger logger = LogManager.getLogger(OfferStatusValidator.class);


    @Override
    public boolean validateUpdatedEntity(HttpServletRequest request) throws ServiceException {

        String idOfferParam = request.getParameter(PARAMETER_OFFER_ID).trim();
        String priceOfferParam = request.getParameter(PARAMETER_OFFER_PRICE).trim();


        if (!Validable.validateStringParameterIntegerClass(priceOfferParam, MAXIMUM_PRICE, MINIMUM_PRICE)) {
            logger.error("Fail validation price offer");
            return false;
        }
        if (!Validable.validateStringParameterIntegerClass(idOfferParam, MAXIMUM_NUMBER_REQUEST, MINIMUM_ZERO)) {
            logger.error("Fail validation offer id");
            return false;
        }
        return true;
    }

    @Override
    public boolean validateNewEntity(HttpServletRequest request) throws ServiceException {
        return false;
    }
}

