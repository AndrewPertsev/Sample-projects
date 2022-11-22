package by.epam.heritage.ap.controller.impl.commands_goto;

import by.epam.heritage.ap.controller.Commandable;
import by.epam.heritage.ap.model.Offer;
import by.epam.heritage.ap.service.ServiceException;
import by.epam.heritage.ap.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static by.epam.heritage.ap.controller.ConstantsCommandPath.PATH_GO_TO_GUEST_ROOM_PAGE;
import static by.epam.heritage.ap.controller.ConstantsParametersAndAttributes.*;

public class GoToGuestRoomCommand implements Commandable {
    private static final Logger logger = LogManager.getLogger(GoToGuestRoomCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);

        try {
           int userId = Integer.parseInt(String.valueOf(session.getAttribute(SESSION_ATTRIBUTE_GUEST_ID)));
            List<Offer>  userOffers = ServiceFactory.getInstance().getOfferService().getOffersByGuestId(userId);
            request.setAttribute(ATTRIBUTE_USER_OFFERS, userOffers);

        } catch (ServiceException e) {
            logger.error("Can't execute request to database", e);
            session.setAttribute(ATTRIBUTE_MESSAGE_FAIL, MESSAGE_DATABASE_ERROR);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(PATH_GO_TO_GUEST_ROOM_PAGE);
        dispatcher.forward(request, response);

    }
}
