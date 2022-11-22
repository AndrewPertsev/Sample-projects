package by.epam.heritage.ap.controller.impl;

import by.epam.heritage.ap.controller.Commandable;
import by.epam.heritage.ap.model.RequestUser;
import by.epam.heritage.ap.service.OfferServiceable;
import by.epam.heritage.ap.service.RequestServiceable;
import by.epam.heritage.ap.service.ServiceException;
import by.epam.heritage.ap.service.ServiceFactory;
import by.epam.heritage.ap.service.validator.Validable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.epam.heritage.ap.controller.ConstantsCommandPath.*;
import static by.epam.heritage.ap.controller.ConstantsParametersAndAttributes.*;
import static by.epam.heritage.ap.service.validator.ValidatorConstants.*;
import static java.lang.Boolean.TRUE;

public class PushOfferToUserCommand implements Commandable {
    private static final Logger logger = LogManager.getLogger(PushOfferToUserCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        boolean done = false;
        int idRequestInt = 0;
        HttpSession session = request.getSession(true);
        OfferServiceable offerService = ServiceFactory.getInstance().getOfferService();
        RequestServiceable requestService = ServiceFactory.getInstance().getRequestService();

        String idRequest = request.getParameter(PARAMETER_REQUEST_ID);
        boolean validIdRequest = Validable.validateStringParameterIntegerClass(idRequest, MAXIMUM_NUMBER_REQUEST, MINIMUM_ZERO);
        boolean validIdApartment = Validable.validateStringParameterIntegerClass(idRequest, MAXIMUM_NUMBER_APARTMENT, MINIMUM_ZERO);


        if (validIdRequest && validIdApartment) {

            request.setAttribute(PARAMETER_REQUEST_ID, idRequest);

            try {
                int apartmentId = Integer.parseInt(request.getParameter(PARAMETER_APARTMENT_ID));
                idRequestInt = Integer.parseInt(idRequest);
                offerService.add(offerService.assembleOfferForApartment(apartmentId, idRequestInt));
                requestService.setRequestIsRespondedStatus(TRUE, idRequestInt);
                RequestUser usersRequestUser = requestService.findById(idRequestInt);
                done = ServiceFactory.getInstance().getTimesheetService()
                        .reserveConfirmedDaysByOffer(apartmentId, usersRequestUser.getStart(), usersRequestUser.getEnd());

            } catch (ServiceException e) {
                logger.error("Can't execute request to database", e);
                session.setAttribute(ATTRIBUTE_MESSAGE_FAIL, MESSAGE_DATABASE_ERROR);
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }

            if (done) {
                session.setAttribute(ATTRIBUTE_MESSAGE_SUCCESS, MESSAGE_SUCCESS_PUSH_TO_USER);
                response.sendRedirect(PATH_REDIRECT_CONTROLLER_COMMAND + GO_TO_OFFER_MANAGEMENT_PAGE + "&" + PARAMETER_REQUEST_ID + "=" + idRequestInt);
            } else {
                session.setAttribute(ATTRIBUTE_MESSAGE_FAIL, MESSAGE_FAIL_PUSH_TO_USER);
                response.sendRedirect(PATH_REDIRECT_CONTROLLER_COMMAND + GO_TO_REQUEST_MANAGEMENT_PAGE);
            }
        } else {
            session.setAttribute(ATTRIBUTE_MESSAGE_FAIL, MESSAGE_FAIL_PUSH_TO_USER);
            response.sendRedirect(PATH_REDIRECT_CONTROLLER_COMMAND + GO_TO_REQUEST_MANAGEMENT_PAGE);
        }
    }
}

