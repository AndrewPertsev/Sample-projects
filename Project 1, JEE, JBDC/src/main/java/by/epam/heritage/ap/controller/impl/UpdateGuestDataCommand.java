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

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.epam.heritage.ap.controller.ConstantsCommandPath.GO_TO_GUEST_MANAGEMENT_PAGE;
import static by.epam.heritage.ap.controller.ConstantsCommandPath.PATH_REDIRECT_CONTROLLER_COMMAND;
import static by.epam.heritage.ap.controller.ConstantsParametersAndAttributes.*;

public class UpdateGuestDataCommand implements Commandable {
    private static final Logger logger = LogManager.getLogger(UpdateGuestDataCommand.class);


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idGuest = 0;
        boolean done = false;
        Boolean isValidGuest = true;
        HttpSession session = request.getSession(true);

        try {
            isValidGuest =  ValidatorFactory.getInstance().getGuestValidator().validateUpdatedEntity(request);
        } catch (ServiceException e) {
            logger.error("Fail validation updated guest");
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }


        if (isValidGuest) {

            try {
               User guestUpdated = BuilderFactory.getInstance().getGuestBuilder().update(request);
                idGuest = (Integer.parseInt(request.getParameter(PARAMETER_GUEST_ID)));
                done = ServiceFactory.getInstance().getUserService().update(guestUpdated);
            } catch (ServiceException | ValidatorException e) {
                logger.error("Can't execute request to database", e);
                request.setAttribute(ATTRIBUTE_MESSAGE_FAIL, MESSAGE_DATABASE_ERROR);
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }

            if (done) {
                session.setAttribute(ATTRIBUTE_MESSAGE_SUCCESS, MESSAGE_SUCCESS_UPDATE_GUEST);
                response.sendRedirect(PATH_REDIRECT_CONTROLLER_COMMAND + GO_TO_GUEST_MANAGEMENT_PAGE +  PARAMETER_GUEST_IDEQ + idGuest);
            } else {
                session.setAttribute(ATTRIBUTE_MESSAGE_FAIL, MESSAGE_FAIL_UPDATE_GUEST);
                response.sendRedirect(PATH_REDIRECT_CONTROLLER_COMMAND + GO_TO_GUEST_MANAGEMENT_PAGE+ PARAMETER_GUEST_IDEQ + idGuest);
            }

        } else {
            session.setAttribute(ATTRIBUTE_MESSAGE_FAIL, MESSAGE_FAIL_UPDATE_GUEST);
            response.sendRedirect(PATH_REDIRECT_CONTROLLER_COMMAND + GO_TO_GUEST_MANAGEMENT_PAGE);
        }
    }
}
