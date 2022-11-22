package by.epam.heritage.ap.controller.impl.commands_goto;

import by.epam.heritage.ap.controller.Commandable;
import by.epam.heritage.ap.model.Apartment;
import by.epam.heritage.ap.model.Offer;
import by.epam.heritage.ap.model.RequestUser;
import by.epam.heritage.ap.service.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static by.epam.heritage.ap.controller.ConstantsCommandPath.PATH_GO_TO_OFFER_PROJECT_PAGE;
import static by.epam.heritage.ap.controller.ConstantsParametersAndAttributes.*;

public class GoToOfferProjectPageCommand implements Commandable {
    private static final Logger logger = LogManager.getLogger(GoToOfferProjectPageCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Offer offer = null;
        List<Offer> offers = new ArrayList<>(0);
        HttpSession session = request.getSession(true);

        String idRequestGuest = request.getParameter(PARAMETER_REQUEST_ID);
        request.setAttribute(PARAMETER_REQUEST_ID, idRequestGuest);

        try {
            RequestServiceable requestService = ServiceFactory.getInstance().getRequestService();
            int idRequestUsersInt = Integer.parseInt(idRequestGuest);

            RequestUser requestUserGuest = requestService.findById(idRequestUsersInt);
            ApartmentServiceable apartmentService = ServiceFactory.getInstance().getApartmentService();
            List<Apartment> suitableApartments = apartmentService.findApartmentsSuitableForRequest(requestUserGuest);

            for (int i = 0; i < suitableApartments.size(); i++) {
                int tempId = suitableApartments.get(i).getApartmentId();
                try {
                    offer = ServiceFactory.getInstance().getOfferService().assembleOfferForApartment(tempId, idRequestUsersInt);
                } catch (ServiceException e) {
                    logger.error("Can't execute request to database", e);
                    session.setAttribute(ATTRIBUTE_MESSAGE_FAIL, MESSAGE_DATABASE_ERROR);
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                }
                offers.add(offer);
            }

            request.setAttribute(ATTRIBUTE_OFFERS, offers);

            RequestDispatcher dispatcher = request.getRequestDispatcher(PATH_GO_TO_OFFER_PROJECT_PAGE);
            dispatcher.forward(request, response);
        } catch (ServiceException e) {
            logger.error("Can't execute request to database", e);
            session.setAttribute(ATTRIBUTE_MESSAGE_FAIL, MESSAGE_DATABASE_ERROR);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}

