package by.epam.heritage.ap.controller.impl;

import by.epam.heritage.ap.controller.Commandable;
import by.epam.heritage.ap.service.validator.Validable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.epam.heritage.ap.controller.ConstantsCommandPath.GO_TO_GUEST_MANAGEMENT_PAGE;
import static by.epam.heritage.ap.controller.ConstantsCommandPath.PATH_REDIRECT_CONTROLLER_COMMAND;
import static by.epam.heritage.ap.controller.ConstantsParametersAndAttributes.*;
import static by.epam.heritage.ap.service.validator.ValidatorConstants.MAXIMUM_NUMBER_APARTMENT;
import static by.epam.heritage.ap.service.validator.ValidatorConstants.MINIMUM_ZERO;

public class ShowGuestData implements Commandable {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String idGuestParam = request.getParameter(PARAMETER_GUEST_ID);
        HttpSession session = request.getSession(true);

        boolean  validParameter = Validable.validateStringParameterIntegerClass(idGuestParam, MAXIMUM_NUMBER_APARTMENT, MINIMUM_ZERO);

        if (validParameter) {
            int idGuest = (Integer.parseInt(idGuestParam));
            response.sendRedirect(PATH_REDIRECT_CONTROLLER_COMMAND + GO_TO_GUEST_MANAGEMENT_PAGE +  PARAMETER_GUEST_IDEQ  + idGuest);
        } else {
            session.setAttribute(ATTRIBUTE_MESSAGE_FAIL, MESSAGE_FAIL);
            response.sendRedirect(PATH_REDIRECT_CONTROLLER_COMMAND + GO_TO_GUEST_MANAGEMENT_PAGE);
        }
    }
}