package by.epam.heritage.ap.controller.impl;

import by.epam.heritage.ap.controller.Commandable;
import by.epam.heritage.ap.model.User;
import by.epam.heritage.ap.service.ServiceException;
import by.epam.heritage.ap.service.ServiceFactory;
import by.epam.heritage.ap.service.builder.BuilderFactory;
import by.epam.heritage.ap.service.validator.ValidatorException;
import by.epam.heritage.ap.service.validator.ValidatorFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.epam.heritage.ap.controller.ConstantsCommandPath.*;
import static by.epam.heritage.ap.controller.ConstantsParametersAndAttributes.*;

public class RegistrationCommand implements Commandable {
    private static final Logger logger = LogManager.getLogger(RegistrationCommand.class);


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Boolean isValidGuest = false;
        boolean isValidCandidate = false;
        HttpSession session = request.getSession(true);

        try {
            isValidGuest =ValidatorFactory.getInstance().getGuestValidator().validateNewEntity(request);
        } catch (ServiceException e) {
            logger.error("Fail validation new guest");
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }

        if (isValidGuest) {

            try {
              User  guestCandidate = BuilderFactory.getInstance().getGuestBuilder().create(request);
                isValidCandidate =  ServiceFactory.getInstance().getUserService().add(guestCandidate);
            } catch (ServiceException | ValidatorException e) {
                logger.error("Can't execute request to database", e);
                request.setAttribute(ATTRIBUTE_MESSAGE_FAIL, MESSAGE_DATABASE_ERROR);
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

            }

            if (isValidCandidate) {
                session.setAttribute(ATTRIBUTE_MESSAGE_SUCCESS, MESSAGE_SUCCESS_REGISTRATION);
                response.sendRedirect(PATH_REDIRECT_CONTROLLER_COMMAND + GO_TO_HOME_PAGE);
            } else {
                session.setAttribute(ATTRIBUTE_MESSAGE_FAIL, MESSAGE_FAIL_REGISTRATION);
                response.sendRedirect(PATH_REDIRECT_CONTROLLER_COMMAND + GO_TO_REGISTRATION_PAGE);
            }
        } else {
            session.setAttribute(ATTRIBUTE_MESSAGE_FAIL, MESSAGE_FAIL_REGISTRATION);
            response.sendRedirect(PATH_REDIRECT_CONTROLLER_COMMAND + GO_TO_REGISTRATION_PAGE);
        }
    }
}
