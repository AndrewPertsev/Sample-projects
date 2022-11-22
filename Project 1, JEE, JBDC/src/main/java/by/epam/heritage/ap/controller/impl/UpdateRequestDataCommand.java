package by.epam.heritage.ap.controller.impl;

import by.epam.heritage.ap.controller.Commandable;
import by.epam.heritage.ap.model.RequestUser;
import by.epam.heritage.ap.service.RequestServiceable;
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

import static by.epam.heritage.ap.controller.ConstantsCommandPath.GO_TO_REQUEST_MANAGEMENT_PAGE;
import static by.epam.heritage.ap.controller.ConstantsCommandPath.PATH_REDIRECT_CONTROLLER_COMMAND;
import static by.epam.heritage.ap.controller.ConstantsParametersAndAttributes.*;

public class UpdateRequestDataCommand implements Commandable {
    private static final Logger logger = LogManager.getLogger(UpdateRequestDataCommand.class);


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        boolean done = false;
        HttpSession session = request.getSession(true);

        boolean isValidRequest = ValidatorFactory.getInstance().getRequestValidator().validateUpdatedEntity(request);

        if (isValidRequest) {

            try {
                RequestUser usersRequestUser = BuilderFactory.getInstance().getRequestBuilder().update(request);
                RequestServiceable requestService = ServiceFactory.getInstance().getRequestService();
                done = requestService.update(usersRequestUser);
            } catch (ServiceException | ValidatorException e) {
                logger.error("Can't execute request to database", e);
                session.setAttribute(ATTRIBUTE_MESSAGE_FAIL, MESSAGE_DATABASE_ERROR);
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }

            if (done) {
                session.setAttribute(ATTRIBUTE_MESSAGE_SUCCESS, MESSAGE_SUCCESS_UPDATE_REQUEST);
                response.sendRedirect(PATH_REDIRECT_CONTROLLER_COMMAND + GO_TO_REQUEST_MANAGEMENT_PAGE);
            } else {
                session.setAttribute(ATTRIBUTE_MESSAGE_FAIL, MESSAGE_FAIL_UPDATE_REQUEST);
                response.sendRedirect(PATH_REDIRECT_CONTROLLER_COMMAND + GO_TO_REQUEST_MANAGEMENT_PAGE);
            }

        } else {
            session.setAttribute(ATTRIBUTE_MESSAGE_FAIL, MESSAGE_FAIL_UPDATE_REQUEST);
            response.sendRedirect(PATH_REDIRECT_CONTROLLER_COMMAND + GO_TO_REQUEST_MANAGEMENT_PAGE);
        }
    }
}



