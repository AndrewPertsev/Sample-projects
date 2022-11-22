package by.epam.heritage.ap.controller.impl;

import by.epam.heritage.ap.controller.Commandable;
import by.epam.heritage.ap.service.Email.EmailSender;
import by.epam.heritage.ap.service.ServiceException;
import by.epam.heritage.ap.service.ServiceFactory;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static by.epam.heritage.ap.controller.ConstantsCommandPath.*;
import static by.epam.heritage.ap.controller.ConstantsParametersAndAttributes.*;

public class SendEmailCommand implements Commandable {
    private final static Logger logger = LogManager.getLogger(SendEmailCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpSession session = request.getSession(true);

        try {

            int requestId = Integer.parseInt(request.getParameter(PARAMETER_REQUEST_ID));
            int userId = ServiceFactory.getInstance().getRequestService().findById(requestId).getUserId();
            String mailTo = ServiceFactory.getInstance().getUserService().findById(userId).getEmail();
            new EmailSender().send(mailTo, TEXT_MAIL_SUBJECT, TEXT_MAIL_MESSAGE);

        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Connection to database failed or Sending message failed", e);
            session.setAttribute(ATTRIBUTE_MESSAGE_FAIL, MESSAGE_EMAIL_ERROR);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

        session.setAttribute(ATTRIBUTE_MESSAGE_SUCCESS, MESSAGE_SUCCESS_PUSH_TO_USER);
        response.sendRedirect(PATH_REDIRECT_CONTROLLER_COMMAND + GO_TO_OFFER_MANAGEMENT_PAGE);
    }
}

