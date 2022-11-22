package by.epam.heritage.ap.service.impl;

import by.epam.heritage.ap.model.Apartment;
import by.epam.heritage.ap.model.RequestUser;
import by.epam.heritage.ap.repository.ApartmentDao;
import by.epam.heritage.ap.repository.DAOException;
import by.epam.heritage.ap.repository.DaoFactory;
import by.epam.heritage.ap.service.ApartmentServiceable;
import by.epam.heritage.ap.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ApartmentServiceImpl implements ApartmentServiceable {
    private static final Logger logger = LogManager.getLogger(ApartmentServiceImpl.class);
    ApartmentDao apartmentDao = DaoFactory.getInstance().getApartmentDao();

    @Override
    public boolean add(Apartment entity) throws ServiceException {
        boolean done;

        try {
            done = apartmentDao.insert(entity);
        } catch (DAOException e) {
            logger.error("Service can't add element ", e);
            throw new ServiceException(e);
        }
        return done;
    }

    @Override
    public boolean deleteByid(int id) throws ServiceException {
        boolean done ;

        try {
            done = apartmentDao.deleteById(id);
        } catch (DAOException e) {
            logger.error("Service can't delete element ", e);
            throw new ServiceException(e);
        }
        return done;
    }


    @Override
    public List<Apartment> findAll() throws ServiceException {
        List<Apartment> rooms;
        try {
            rooms = apartmentDao.findAll();
        } catch (DAOException e) {
            logger.error("Service can't find element ", e);
            throw new ServiceException(e);
        }
        return rooms;
    }


    @Override
    public List<Apartment> findApartmentsSuitableForRequest(RequestUser requestUser) throws ServiceException {
        List<Apartment> suitableApartments;
        try {
            suitableApartments = apartmentDao.findApartmentsSuitableForRequest(requestUser);
        } catch (DAOException e) {
            logger.error("Service can't find element ", e);
            throw new ServiceException(e);
        }

        return suitableApartments;
    }


    @Override
    public boolean update(Apartment apartment) throws ServiceException {
        boolean done ;

        try {
            done = apartmentDao.update(apartment);
        } catch (DAOException e) {
            logger.error("Service can't update element ", e);
            throw new ServiceException(e);
        }
        return done;
    }

    @Override
    public Apartment findById(int id) throws ServiceException {
        Apartment apartment;
        try {
            apartment = apartmentDao.findById(id);
        } catch (DAOException e) {
            logger.error("Service can't find element ", e);
            throw new ServiceException(e);
        }

        return apartment;
    }

}

