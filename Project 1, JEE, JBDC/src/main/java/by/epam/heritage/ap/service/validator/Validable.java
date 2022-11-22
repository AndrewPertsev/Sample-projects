package by.epam.heritage.ap.service.validator;

import by.epam.heritage.ap.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static by.epam.heritage.ap.service.validator.ValidatorConstants.PATTERN_DIGIT;

/**
 * Interface for validators.
 */
public interface Validable {
    /**
     * Checks new entity.
     *
     * @param request HttpServletRequest
     * @return boolean true if the entity is valid.
     * @throws ServiceException
     */
    boolean validateNewEntity(HttpServletRequest request) throws ServiceException;

    /**
     * Checks updated entity.
     *
     * @param request
     * @return boolean true if the entity is valid.
     * @throws ServiceException
     * @throws ValidatorException
     */
    boolean validateUpdatedEntity(HttpServletRequest request) throws ServiceException, ValidatorException;


    /**
     * Validates String class parameter.
     *
     * @param parameter incoming parameter.
     * @param pattern   Regexp.
     * @return boolean true if the entity is valid.
     */
    static boolean validateParameterStringClass(String parameter, String pattern) {
        Logger logger = LogManager.getLogger(Validable.class);
        if (parameter == null || !(parameter.matches(pattern))) {
            logger.error("Parameter \"" + parameter + "\" is not valid, or empty");
            return false;
        } else {
            return true;
        }
    }

    /**
     * Validates Integer class parameter, that becomes in String format.
     *
     * @param parameterToInt String parameter
     * @param maximum        value of the parameter parameterToInt
     * @param minimum        value of the parameter parameterToInt
     * @return boolean true if the entity is valid.
     */

    static boolean validateStringParameterIntegerClass(String parameterToInt, int maximum, int minimum) {
        Logger logger = LogManager.getLogger(Validable.class);
        int parsedParameter;

        if (!Validable.validateParameterStringClass(parameterToInt, PATTERN_DIGIT)) {
            logger.error("Parameter \"" + parameterToInt + "\" is not digit ");
            return false;
        }

        parsedParameter = Integer.parseInt(parameterToInt);

        if (parsedParameter <= minimum || parsedParameter > maximum) {
            logger.error("Parameter \"" + parameterToInt + "\" is not valid ");
            return false;
        }
        return true;
    }
}
