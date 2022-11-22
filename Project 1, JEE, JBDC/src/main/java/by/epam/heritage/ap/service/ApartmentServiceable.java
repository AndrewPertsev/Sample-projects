package by.epam.heritage.ap.service;

import by.epam.heritage.ap.model.Apartment;
import by.epam.heritage.ap.model.RequestUser;

import java.util.List;

public interface ApartmentServiceable extends Serviceable<Apartment> {
    /**
     * Finds apartment, suitable for the user's request.
     *
     * @param requestUser
     * @return Apartments list.
     * @throws ServiceException
     */
    List<Apartment> findApartmentsSuitableForRequest(RequestUser requestUser) throws ServiceException;

    /**
     * Delete entity by id.
     *
     * @param id
     * @return boolean true if the entity has been deleted, else returns false.
     * @throws ServiceException
     */
    boolean deleteByid(int id) throws ServiceException;

}
