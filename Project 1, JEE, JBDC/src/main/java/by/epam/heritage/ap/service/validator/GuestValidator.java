package by.epam.heritage.ap.service.validator;

import by.epam.heritage.ap.model.User;
import by.epam.heritage.ap.repository.DAOException;
import by.epam.heritage.ap.repository.DaoFactory;
import by.epam.heritage.ap.repository.UserDao;
import by.epam.heritage.ap.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;

import static by.epam.heritage.ap.controller.ConstantsParametersAndAttributes.*;
import static by.epam.heritage.ap.service.validator.ValidatorConstants.*;

public final class GuestValidator implements Validable {
    private static final Logger logger = LogManager.getLogger(GuestValidator.class);

    @Override
    public boolean validateNewEntity(HttpServletRequest request) throws ServiceException {
        HttpSession session = request.getSession(true);

        String surname = request.getParameter(PARAMETER_SURNAME).trim();
        String name = request.getParameter(PARAMETER_NAME).trim();
        String email = request.getParameter(PARAMETER_EMAIL).trim();
        String login = request.getParameter(PARAMETER_LOGIN).trim();
        String phone = request.getParameter(PARAMETER_PHONE).trim();
        String country = request.getParameter(PARAMETER_COUNTRY).trim();
        String passport = request.getParameter(PARAMETER_PASSPORT_NUMBER).trim();
        char[] password = (request.getParameter(PARAMETER_PASSWORD).toCharArray());
        char[] password2 = (request.getParameter(PARAMETER_PASSWORD2).toCharArray());

        if (!Validable.validateParameterStringClass(name, PATTERN_NAME)
                || !Validable.validateParameterStringClass(surname, PATTERN_NAME)) {
            logger.error("Fail validation name or surname ");
            session.setAttribute(ATTRIBUTE_EXCEPTION, "Fail validation name or surname");
            return false;
        }

        if (!validateLogin(login)
                || !validatePassword(password, password2)) {
            logger.error("Fail validation login or password");
            session.setAttribute(ATTRIBUTE_EXCEPTION, "Fail validation login or password");
            return false;
        }

        if (!Validable.validateParameterStringClass(email, PATTERN_EMAIL)) {
            logger.error("Fail validation email ");
            session.setAttribute(ATTRIBUTE_EXCEPTION, "Fail validation email");
            return false;
        }

        if (!Validable.validateParameterStringClass(phone, PATTERN_PHONE)
                || !Validable.validateParameterStringClass(country, PATTERN_NAME)
                || !Validable.validateParameterStringClass(passport, PATTERN_PASSPORT_NUMBER)) {
            logger.error("Fail validation phone, passport or country");
            session.setAttribute(ATTRIBUTE_EXCEPTION, "Fail validation phohe, passport or country");
            return false;
        }


        return true;
    }

    @Override
    public boolean validateUpdatedEntity(HttpServletRequest request) throws ServiceException {
        HttpSession session = request.getSession(true);

        String surname = request.getParameter(PARAMETER_SURNAME).trim();
        String name = request.getParameter(PARAMETER_NAME).trim();
        String email = request.getParameter(PARAMETER_EMAIL).trim();
        String phone = request.getParameter(PARAMETER_PHONE).trim();
        String roleId = request.getParameter(PARAMETER_ROLE_ID).trim();
        String guestId = request.getParameter(PARAMETER_GUEST_ID).trim();
        String comment = request.getParameter(PARAMETER_COMMENT).trim();

        if (!Validable.validateStringParameterIntegerClass(guestId, MAXIMUM_NUMBER_USER_ID, MINIMUM_ZERO)
                || !Validable.validateStringParameterIntegerClass(roleId, MAXIMUM_ROLE_ID, MINIMUM_ZERO - 1)) {
            logger.error("Fail validation guest id or role id");
            session.setAttribute(ATTRIBUTE_EXCEPTION, "User id or role id is invalid");
            return false;
        }

        if (!Validable.validateParameterStringClass(name, PATTERN_NAME)
                || !Validable.validateParameterStringClass(surname, PATTERN_NAME)) {
            logger.error("Fail validation name or surname ");
            session.setAttribute(ATTRIBUTE_EXCEPTION, "Fail validation name or surname");
            return false;
        }

        if (!Validable.validateParameterStringClass(email, PATTERN_EMAIL)) {
            logger.error("Fail validation email ");
            session.setAttribute(ATTRIBUTE_EXCEPTION, "Fail validation email");
            return false;
        }

        if (!Validable.validateParameterStringClass(phone, PATTERN_PHONE)
                || !Validable.validateParameterStringClass(comment, PATTERN_COMMENT)) {
            logger.error("Fail validation phone or comment");
            session.setAttribute(ATTRIBUTE_EXCEPTION, "Fail validation phone  or comment");
            return false;
        }

        return true;
    }


    public boolean validateLogin(String login) throws ServiceException {
        User guest;

        if (!Validable.validateParameterStringClass(login, PATTERN_NAME)) {
            logger.error("Login is not valid ");
            return false;
        } else {
            UserDao userDao = DaoFactory.getInstance().getGuestDao();
            try {
                guest = userDao.findByName(login);
            } catch (DAOException e) {
                logger.error("login is not valid ", e);
                throw new ServiceException(e);
            }
            if (guest.getUserId() != 0) {
                logger.error("Guest is already exists");
                return false;
            } else {
                return true;
            }
        }
    }


    public boolean validatePassword(char[] password, char[] password2) {

        if (!Validable.validateParameterStringClass(password.toString(), PATTERN_PASSWORD)) {
            logger.error("Password array is equal 0 or is not valid ");
            return false;
        }
        if (!Arrays.equals(password2, password)) {
            logger.error("Password arrays are not equal ");
            return false;
        } else {
            return true;
        }
    }


}