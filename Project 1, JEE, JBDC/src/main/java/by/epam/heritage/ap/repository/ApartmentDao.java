package by.epam.heritage.ap.repository;

import by.epam.heritage.ap.model.Apartment;
import by.epam.heritage.ap.model.RequestUser;

import java.util.List;


public interface ApartmentDao extends BaseDAO<Apartment> {

    /**Find all apartments, suitable for the request parameters
     *
     * @param requestUser
     * @return list of Apartments.
     * @throws DAOException
     */
    List<Apartment> findApartmentsSuitableForRequest(RequestUser requestUser) throws DAOException;

    /**Delete Apartment by its id.
     *
     * @param id
     * @return boolean true if the apartment has been deleted, else returns false.
     * @throws DAOException
     */
    boolean deleteById(int id) throws DAOException;

    /**
     *Updates Apartment data.
     * @param entity
     * @return boolean true if the apartment data has been updated, else returns false.
     * @throws DAOException
     */
    boolean update(Apartment entity) throws DAOException;

    /**
     *Adds new Apartment.
     * @param entity
     * @return boolean true if the new Apartment has been added, else returns false.
     * @throws DAOException
     */
    boolean insert(Apartment entity) throws DAOException;

}

