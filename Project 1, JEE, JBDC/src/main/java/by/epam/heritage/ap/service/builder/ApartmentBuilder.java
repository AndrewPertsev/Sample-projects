package by.epam.heritage.ap.service.builder;

import by.epam.heritage.ap.model.Apartment;
import by.epam.heritage.ap.service.ServiceException;
import by.epam.heritage.ap.service.validator.ValidatorException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static by.epam.heritage.ap.controller.ConstantsParametersAndAttributes.*;

public class ApartmentBuilder implements Buildable {
    private static final Logger logger = LogManager.getLogger(ApartmentBuilder.class);

    public Apartment create(HttpServletRequest request){

        Apartment apartment = new Apartment();

        apartment.setApartmentId(Integer.parseInt(request.getParameter(PARAMETER_APARTMENT_ID).trim()));
        apartment.setCapacity(Integer.parseInt(request.getParameter(PARAMETER_CAPACITY).trim()));
        apartment.setCategory(Integer.parseInt(request.getParameter(PARAMETER_CATEGORY).trim()));
        apartment.setDescription((request.getParameter(PARAMETER_DESCRIPTION).trim()));
        apartment.setPathToPicture(request.getParameter(PARAMETER_PATH_TO_PICTURE).trim());
        logger.debug("Apartment builder create");

        return apartment;
    }
}

