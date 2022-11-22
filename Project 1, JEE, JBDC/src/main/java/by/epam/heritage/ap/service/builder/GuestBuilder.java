package by.epam.heritage.ap.service.builder;

import by.epam.heritage.ap.model.User;
import by.epam.heritage.ap.service.ServiceException;
import by.epam.heritage.ap.service.validator.ValidatorException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

import static by.epam.heritage.ap.controller.ConstantsParametersAndAttributes.*;

public class GuestBuilder implements Buildable {
    private static final Logger logger = LogManager.getLogger(GuestBuilder.class);
    private static final char FILLER_PASSWORD_ARRAY = '0';

    public User create(HttpServletRequest request) throws ServiceException, ValidatorException {

        User newGuest = new User();

        char[] password = request.getParameter(PARAMETER_PASSWORD).toCharArray();
        char[] password2 = request.getParameter(PARAMETER_PASSWORD2).toCharArray();
//        String hashPassword = BCrypt.hashpw(request.getParameter(PARAMETER_PASSWORD), BCrypt.gensalt());
        String hashPassword = request.getParameter(PARAMETER_PASSWORD) ;
        Arrays.fill(password, FILLER_PASSWORD_ARRAY);
        Arrays.fill(password2, FILLER_PASSWORD_ARRAY);

        newGuest.setName(request.getParameter(PARAMETER_NAME).trim());
        newGuest.setSurname(request.getParameter(PARAMETER_SURNAME).trim());
        newGuest.setEmail(request.getParameter(PARAMETER_EMAIL).trim());
        newGuest.setTel(request.getParameter(PARAMETER_PHONE).trim());
        newGuest.setLogin(request.getParameter(PARAMETER_LOGIN).trim());
        newGuest.setPassword(hashPassword);
        newGuest.setCountry(request.getParameter(PARAMETER_COUNTRY).trim());
        newGuest.setPassport(Integer.parseInt(request.getParameter(PARAMETER_PASSPORT_NUMBER).trim()));
        logger.debug("Guest builder created");
        return newGuest;
    }


    public User update(HttpServletRequest request) throws ValidatorException {

        User newGuest = new User();

        newGuest.setName(request.getParameter(PARAMETER_NAME).trim());
        newGuest.setSurname(request.getParameter(PARAMETER_SURNAME).trim());
        newGuest.setEmail(request.getParameter(PARAMETER_EMAIL).trim());
        newGuest.setTel(request.getParameter(PARAMETER_PHONE).trim());
        newGuest.setComment(request.getParameter(PARAMETER_COMMENT).trim());
        newGuest.setRoleId(Integer.parseInt(request.getParameter(PARAMETER_ROLE_ID).trim()));
        newGuest.setUserId(Integer.parseInt(request.getParameter(PARAMETER_GUEST_ID).trim()));
        newGuest.setVip(Boolean.parseBoolean(request.getParameter(PARAMETER_GUEST_IS_VIP).trim()));
        newGuest.setNonGrata(Boolean.parseBoolean(request.getParameter(PARAMETER_GUEST_IS_NON_GRATA).trim()));
        logger.debug("Guest builder updated");

        return newGuest;
    }

}