package by.epam.heritage.ap.service.impl;

import by.epam.heritage.ap.model.RequestUser;
import by.epam.heritage.ap.repository.DAOException;
import by.epam.heritage.ap.repository.DaoFactory;
import by.epam.heritage.ap.repository.RequestUserDao;
import by.epam.heritage.ap.service.RequestServiceable;
import by.epam.heritage.ap.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class RequestServiceImpl implements RequestServiceable {
    private static final Logger logger = LogManager.getLogger(RequestServiceImpl.class);
    RequestUserDao requestUserDao = DaoFactory.getInstance().getRequestDao();

    @Override
    public boolean add(RequestUser entity) throws ServiceException {
        boolean done;
        try {
            done = requestUserDao.insert(entity);
        } catch (DAOException e) {
            logger.error("Service can't add element ", e);
            throw new ServiceException(e);
        }
        return done;
    }

    @Override
    public boolean update(RequestUser entity) throws ServiceException {
        boolean done;
        try {
            done = requestUserDao.update(entity);
        } catch (DAOException e) {
            logger.error("Service can't update element ", e);
            throw new ServiceException(e);
        }
        return done;
    }

    public boolean setRequestIsRespondedStatus(boolean isResponded, int idRequest) throws ServiceException {
        try {
            requestUserDao.setRequestIsRespondedStatus(isResponded, idRequest);
        } catch (DAOException e) {
            logger.error("Service can't set request status ", e);
            throw new ServiceException(e);
        }
        return true;
    }


    @Override
    public RequestUser findById(int id) throws ServiceException {
        RequestUser requestUser;
        try {
            requestUser = requestUserDao.findById(id);
        } catch (DAOException e) {
            logger.error("Service can't find element ", e);
            throw new ServiceException(e);
        }
        return requestUser;
    }


    @Override
    public List<RequestUser> findAll() throws ServiceException {
        List<RequestUser> requestUser;
        try {
            requestUser = requestUserDao.findAll();
        } catch (DAOException e) {
            logger.error("Service can't find element ", e);
            throw new ServiceException(e);
        }
        return requestUser;
    }


    @Override
    public boolean deleteByid(int id) throws ServiceException {
        boolean done;
        try {
            done = requestUserDao.deleteByid(id);
        } catch (DAOException e) {
            logger.error("Service can't delete element ", e);
            throw new ServiceException(e);
        }
        return done;
    }

    @Override
    public int findMaximumRequestId() throws ServiceException {
        int idMax;
        try {
            idMax = requestUserDao.findMaximumRequestId();
        } catch (DAOException e) {
            logger.error("Service can't find element ", e);
            throw new ServiceException(e);
        }
        return idMax;
    }

    @Override
    public int findMinimumRequestId() throws ServiceException {
        int idMax;
        try {
            idMax = requestUserDao.findMinimumRequestId();
        } catch (DAOException e) {
            logger.error("Service can't find element ", e);
            throw new ServiceException(e);
        }
        return idMax;
    }

    @Override
    public List<RequestUser> findUnresponded() throws ServiceException {
        List<RequestUser> requestUser;
        try {
            requestUser = requestUserDao.findUnresponded();
        } catch (DAOException e) {
            logger.error("Service can't find element ", e);
            throw new ServiceException(e);
        }
        return requestUser;
    }
}
