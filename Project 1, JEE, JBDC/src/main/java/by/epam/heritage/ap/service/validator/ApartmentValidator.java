package by.epam.heritage.ap.service.validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static by.epam.heritage.ap.controller.ConstantsParametersAndAttributes.*;
import static by.epam.heritage.ap.service.validator.ValidatorConstants.*;

public final class ApartmentValidator implements Validable {
    private static final Logger logger = LogManager.getLogger(ApartmentValidator.class);


    @Override
    public boolean validateNewEntity(HttpServletRequest request) {
        HttpSession session = request.getSession(true);

        String idApartmentParam = request.getParameter(PARAMETER_APARTMENT_ID).trim();
        String categoryParam = request.getParameter(PARAMETER_CATEGORY).trim();
        String capacityParam = request.getParameter(PARAMETER_CAPACITY).trim();
        String description = request.getParameter(PARAMETER_DESCRIPTION).trim();
        String pathToPicture = request.getParameter(PARAMETER_PATH_TO_PICTURE).trim();

        if (!Validable.validateStringParameterIntegerClass(idApartmentParam, MAXIMUM_NUMBER_APARTMENT, MINIMUM_ZERO)) {
            logger.error("Fail validation id apartment ");
            session.setAttribute(ATTRIBUTE_EXCEPTION, "Fail validation id apartment");
            return false;
        }

        if (!Validable.validateStringParameterIntegerClass(capacityParam, MAXIMUM_CAPACITY_APARTMENT, MINIMUM_ZERO)) {
            logger.error("Fail validation capacity ");
            session.setAttribute(ATTRIBUTE_EXCEPTION, "Fail validation capacity");
            return false;
        }

        if (!Validable.validateStringParameterIntegerClass(categoryParam, MAXIMUM_CATEGORY_NUMBER_APARTMENT, MINIMUM_ZERO)) {
            logger.error("Fail validation category ");
            session.setAttribute(ATTRIBUTE_EXCEPTION, "Fail validation category");
            return false;
        }

        if (!Validable.validateParameterStringClass(pathToPicture, PATTERN_PICTURE_PNG)) {
            logger.error("Fail validation picture path ");
            session.setAttribute(ATTRIBUTE_EXCEPTION, "Fail validation picture path");
            return false;
        }

        if (!Validable.validateParameterStringClass(description, PATTERN_DESCRIPTION)) {
            logger.error("Fail validation description ");
            session.setAttribute(ATTRIBUTE_EXCEPTION, "Fail validation description");
            return false;
        }

        return true;
    }

    @Override
    public boolean validateUpdatedEntity(HttpServletRequest request) {
        return new ApartmentValidator().validateNewEntity(request);
    }


}
