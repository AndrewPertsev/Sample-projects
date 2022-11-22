package by.epam.heritage.ap.controller.impl;

import by.epam.heritage.ap.controller.Commandable;
import by.epam.heritage.ap.model.Apartment;
import by.epam.heritage.ap.service.ServiceException;
import by.epam.heritage.ap.service.ServiceFactory;
import by.epam.heritage.ap.service.builder.BuilderFactory;
import by.epam.heritage.ap.service.validator.Validable;
import by.epam.heritage.ap.service.validator.ValidatorFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.epam.heritage.ap.controller.ConstantsCommandPath.GO_TO_APARTMENT_MANAGEMENT_PAGE;
import static by.epam.heritage.ap.controller.ConstantsCommandPath.PATH_REDIRECT_CONTROLLER_COMMAND;
import static by.epam.heritage.ap.controller.ConstantsParametersAndAttributes.*;

public class AddApartmentCommand implements Commandable {
    private static final Logger logger = LogManager.getLogger(AddApartmentCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        boolean done = false;
        Boolean isValidApartment = false;
        HttpSession session = request.getSession(true);

        Validable validator = ValidatorFactory.getInstance().getApartmentValidator();
        try {
            isValidApartment = validator.validateNewEntity(request);
        } catch (ServiceException e) {
            logger.error("Can't validate incoming data", e);
            session.setAttribute(ATTRIBUTE_MESSAGE_FAIL, MESSAGE_INVALID_DATA);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }

        if (isValidApartment) {


            try {
                Apartment  apartmentValidated = BuilderFactory.getInstance().getApartmentBuilder().create(request);
                done = ServiceFactory.getInstance().getApartmentService().add(apartmentValidated);
            } catch (ServiceException e) {
                logger.error("Can't execute request to database", e);
                session.setAttribute(ATTRIBUTE_MESSAGE_FAIL, MESSAGE_DATABASE_ERROR);
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }

            if (done) {
                session.setAttribute(ATTRIBUTE_MESSAGE_SUCCESS, MESSAGE_SUCCESS_ADD_APARTMENT);
                response.sendRedirect(PATH_REDIRECT_CONTROLLER_COMMAND + GO_TO_APARTMENT_MANAGEMENT_PAGE);
            } else {
                session.setAttribute(ATTRIBUTE_MESSAGE_FAIL, MESSAGE_FAIL_ADD_APARTMENT);
                response.sendRedirect(PATH_REDIRECT_CONTROLLER_COMMAND + GO_TO_APARTMENT_MANAGEMENT_PAGE);
            }

        } else {
            session.setAttribute(ATTRIBUTE_MESSAGE_FAIL, MESSAGE_FAIL_ADD_APARTMENT);
            response.sendRedirect(PATH_REDIRECT_CONTROLLER_COMMAND + GO_TO_APARTMENT_MANAGEMENT_PAGE);
        }
    }
}

