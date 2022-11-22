package by.epam.heritage.ap.controller.impl;

import by.epam.heritage.ap.controller.Commandable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.epam.heritage.ap.controller.ConstantsCommandPath.*;
import static by.epam.heritage.ap.controller.ConstantsParametersAndAttributes.PARAMETER_REQUEST_ID;

public class OfferCommand implements Commandable {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String idRequest = request.getParameter(PARAMETER_REQUEST_ID);
        request.setAttribute(PARAMETER_REQUEST_ID, idRequest);

        if (idRequest.equals(null) || idRequest != "") {
            response.sendRedirect(PATH_REDIRECT_CONTROLLER_COMMAND + GO_TO_OFFER_PROJECT_PAGE + "&" + PARAMETER_REQUEST_ID + "=" + idRequest);
        } else {
            response.sendRedirect(PATH_REDIRECT_CONTROLLER_COMMAND + GO_TO_REQUEST_MANAGEMENT_PAGE);
        }

    }
}
