package by.epam.heritage.ap.service.builder;

import by.epam.heritage.ap.model.HotelEntity;
import by.epam.heritage.ap.service.ServiceException;
import by.epam.heritage.ap.service.validator.ValidatorException;

import javax.servlet.http.HttpServletRequest;


public interface Buildable<T extends HotelEntity> {

    /**Creates new entity from HttpServletRequest parameters.
     *
     * @param request
     * @return new entity
     * @throws ServiceException
     * @throws ValidatorException
     */
    T create(HttpServletRequest request) throws ServiceException, ValidatorException;

}
