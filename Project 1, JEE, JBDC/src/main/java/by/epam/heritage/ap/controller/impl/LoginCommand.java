package by.epam.heritage.ap.controller.impl;

import by.epam.heritage.ap.controller.Commandable;
import by.epam.heritage.ap.model.User;
import by.epam.heritage.ap.service.ServiceException;
import by.epam.heritage.ap.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.epam.heritage.ap.controller.ConstantsCommandPath.*;
import static by.epam.heritage.ap.controller.ConstantsParametersAndAttributes.*;

public class LoginCommand implements Commandable {
    private static final Logger logger = LogManager.getLogger(LoginCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(true);
        User authorizedGuest = null;
        String login = request.getParameter(PARAMETER_LOGIN);
        String password = request.getParameter(PARAMETER_PASSWORD);  //todo fill out password

        try {
            authorizedGuest = ServiceFactory.getInstance().getUserService().checkGuestAuthorization(login, password);
        } catch (ServiceException e) {
            logger.error("Can't execute request to database", e);
            request.setAttribute(ATTRIBUTE_MESSAGE_FAIL, MESSAGE_DATABASE_ERROR);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

        if (authorizedGuest != null) {

            session.setAttribute(SESSION_ATTRIBUTE_GUEST_ID, String.valueOf(authorizedGuest.getUserId()));
            session.setAttribute(SESSION_ATTRIBUTE_GUEST_ROLE_ID, String.valueOf(authorizedGuest.getRoleId()));
            session.setAttribute(SESSION_ATTRIBUTE_GUEST_NAME, authorizedGuest.getName());
            session.setAttribute(SESSION_ATTRIBUTE_GUEST_SUR_NAME, authorizedGuest.getSurname());
            session.setAttribute(SESSION_ATTRIBUTE_GUEST_EMAIL, authorizedGuest.getEmail());
            session.setAttribute(SESSION_ATTRIBUTE_GUEST_PHONE, authorizedGuest.getTel());
            session.setAttribute(SESSION_ATTRIBUTE_GUEST_VIP, authorizedGuest.isVip());
            logger.debug("User session started");

            session.setAttribute(ATTRIBUTE_MESSAGE_SUCCESS, MESSAGE_SUCCESS_LOGIN);
            response.sendRedirect(PATH_REDIRECT_CONTROLLER_COMMAND + GO_TO_MAIN_PAGE + PATH_MESSAGE_SUCCESS);
        } else {
            session.setAttribute(ATTRIBUTE_MESSAGE_FAIL, MESSAGE_FAIL_LOGIN);
            response.sendRedirect(PATH_REDIRECT_CONTROLLER_COMMAND + GO_TO_LOGIN_PAGE);
        }

    }
}





