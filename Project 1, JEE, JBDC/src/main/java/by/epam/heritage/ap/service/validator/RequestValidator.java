package by.epam.heritage.ap.service.validator;

import by.epam.heritage.ap.service.RequestServiceable;
import by.epam.heritage.ap.service.ServiceException;
import by.epam.heritage.ap.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;

import static by.epam.heritage.ap.controller.ConstantsParametersAndAttributes.*;
import static by.epam.heritage.ap.service.validator.ValidatorConstants.*;

public final class RequestValidator implements Validable {
    private static final Logger logger = LogManager.getLogger(RequestValidator.class);

    @Override
    public boolean validateNewEntity(HttpServletRequest request) {
        HttpSession session = request.getSession(true);

        boolean isValid = true;
        String dateout = String.valueOf(request.getParameter(PARAMETER_CHECK_OUT_DATE)).trim();
        String dateIn = String.valueOf(request.getParameter(PARAMETER_CHECK_IN_DATE)).trim();
        String category = request.getParameter(PARAMETER_CATEGORY).trim();
        String quantity = request.getParameter(PARAMETER_QUANTITY).trim();
        String transfer = request.getParameter(PARAMETER_TRANSFER).trim();
        String menu = request.getParameter(PARAMETER_MENU).trim();


        if (!validateRequestBookingDate(dateIn, dateout)) {
            logger.error("Fail validation date ");
            session.setAttribute(ATTRIBUTE_EXCEPTION, "Fail validation check-in or check-out date");
            return false;
        }

        if (!Validable.validateStringParameterIntegerClass(quantity, MAXIMUM_QUANTITY_PERSONS, MINIMUM_ZERO)
                || !Validable.validateStringParameterIntegerClass(category, MAXIMUM_CATEGORY_NUMBER_APARTMENT, MINIMUM_ZERO)
                || !Validable.validateStringParameterIntegerClass(transfer, MAXIMUM_TRANSFER_ID, MINIMUM_ZERO)
                || !Validable.validateStringParameterIntegerClass(menu, MAXIMUM_MENU_ID, MINIMUM_ZERO)) {
            logger.error("Fail validation quantity, category, transfer id or menu id");
            session.setAttribute(ATTRIBUTE_EXCEPTION, "Fail validation quantity, category, transfer id or menu id");
            return false;
        }
        return isValid;

    }

    @Override
    public boolean validateUpdatedEntity(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        String requestId = request.getParameter(PARAMETER_REQUEST_ID).trim();
        int currentIdRequestMax = MAXIMUM_NUMBER_REQUEST;
        int currentIdRequestMin = MINIMUM_NUMBER_REQUEST;
        RequestServiceable service = ServiceFactory.getInstance().getRequestService();

        try {
            currentIdRequestMax = service.findMaximumRequestId();
            currentIdRequestMin = service.findMinimumRequestId() - MINIMUM_NUMBER_REQUEST;
        } catch (ServiceException e) {
            session.setAttribute(ATTRIBUTE_MESSAGE_FAIL, MESSAGE_INVALID_DATA);
        }

        if (!Validable.validateStringParameterIntegerClass(requestId, currentIdRequestMax, currentIdRequestMin)) {
            logger.error("Fail validation request id ");
            session.setAttribute(ATTRIBUTE_EXCEPTION, "Fail validation request id, out of current bounds or wrong format");
            return false;
        }
        return validateNewEntity(request);

    }


    public boolean validateRequestBookingDate(String startDate, String endDate) {

        if (!startDate.matches(PATTERN_DATE)
                | !endDate.matches(PATTERN_DATE)
                | startDate.equals(null)
                | startDate == ""
                | endDate.equals(null)
                | endDate == "") {
            logger.error("Service can't validate start or end date ");
            return false;
        }

        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);

        if (end.isEqual(start)) {
            logger.error("Service can't validate equal dates ");
            return false;
        }
        if (start.isBefore(LocalDate.now())) {
            logger.error("Service can't validate start date when it's  before now");
            return false;
        }
        if (end.isAfter(LocalDate.now().plusDays(MAXIMUM_DAYS_FOR_BOOKING))) {
            logger.error("Service can't validate end date, because period greater than" + MAXIMUM_DAYS_FOR_BOOKING + " days ");
            return false;
        }

        return true;
    }


}
