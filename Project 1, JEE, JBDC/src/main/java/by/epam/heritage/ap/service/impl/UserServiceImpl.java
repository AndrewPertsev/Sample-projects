package by.epam.heritage.ap.service.impl;

import by.epam.heritage.ap.model.User;
import by.epam.heritage.ap.repository.DAOException;
import by.epam.heritage.ap.repository.DaoFactory;
import by.epam.heritage.ap.repository.UserDao;
import by.epam.heritage.ap.service.ServiceException;
import by.epam.heritage.ap.service.UserServiceable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class UserServiceImpl implements UserServiceable {
    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);
    UserDao userDao = DaoFactory.getInstance().getGuestDao();

    public UserServiceImpl() {
    }

    @Override
    public User findById(int idGuest) throws ServiceException {
        User guest;
        try {
            guest = userDao.findById(idGuest);
        } catch (DAOException e) {
            logger.error("Service can't find element ", e);
            throw new ServiceException(e);
        }
        return guest;

    }

    @Override
    public boolean add(User entity) throws ServiceException {
        boolean done;
        try {
            done = userDao.insert(entity);
        } catch (DAOException e) {
            logger.error("Service can't add element ", e);
            throw new ServiceException(e);
        }
        return done;
    }

    @Override
    public boolean update(User entity) throws ServiceException {
        boolean done;
        try {
            done = userDao.update(entity);
        } catch (DAOException e) {
            logger.error("Service can't update element ", e);
            throw new ServiceException(e);
        }
        if (done == false) {
            logger.error("Service  command can't update element ");
        }
        return done;
    }


    public final User checkGuestAuthorization(String login, String passwordToCheck) throws ServiceException {
        User validGuest;

        try {
            validGuest = userDao.findByName(login);
        } catch (DAOException e) {
            logger.error("Service can't  find  guest", e);
            throw new ServiceException(e);
        }
        if (validGuest == null) {
            logger.error("Service can't validate null guest ");
            return null;
        }
        if (validGuest.getUserId() == 0) {
            logger.error("Service can't validate guest id");
            return null;
        }
        if (validGuest.isNonGrata()) {
            logger.error("Service can't validate non grata status ");
            return null;
        }
//        if (!BCrypt.checkpw(passwordToCheck, validGuest.getPassword())) {
//            logger.error("Service can't validate password or login");
//            return null;
//        }
        if (!validGuest.getPassword().equals(passwordToCheck)) {
            logger.error("Service can't validate password or login ");
            return null;
        }
        return validGuest;
    }


    @Override
    public List<User> findAll() throws ServiceException {
        List<User> list;
        try {
            list = userDao.findAll();
        } catch (DAOException e) {
            logger.error("Service can't find element ", e);
            throw new ServiceException(e);
        }
        return list;

    }


}