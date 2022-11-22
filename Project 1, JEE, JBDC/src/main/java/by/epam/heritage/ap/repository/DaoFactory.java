package by.epam.heritage.ap.repository;

import by.epam.heritage.ap.repository.connection.PoolException;
import by.epam.heritage.ap.repository.impl.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class DaoFactory {
    private static final Logger logger = LogManager.getLogger(DaoFactory.class);

    private static volatile DaoFactory instance;

    private final ApartmentDao apartmentDao = new ApartmentDaoImpl();
    private final TimesheetDao timesheetDao = new TimesheetDaoImpl();
    private final CategoryDao categoryDao = new CategoryDaoImpl();
    private final TransferDao transferDao = new TransferDaoImpl();
    private final RequestUserDao requestUserDao = new RequestUserDaoImpl();
    private final MenuDao menuDao = new MenuDaoImpl();
    private final UserDao userDao = new UserDaoImpl();
    private final OfferDao offerDao = new OfferDaoImpl();

    DaoFactory() throws DAOException, PoolException {
    }

    public static DaoFactory getInstance() {
        if (instance == null) {
            synchronized (DaoFactory.class) {
                if (instance == null) {
                    try {
                        instance = new DaoFactory();
                    } catch (DAOException | PoolException e) {
                        logger.error("DaoFactory has not created", e);
                        throw new RuntimeException("DaoFactory has not created", e);
                    }
                }
            }
        }
        return instance;
    }

    public ApartmentDao getApartmentDao() {
        return apartmentDao;
    }

    public TimesheetDao getTimesheetDao() {
        return timesheetDao;
    }

    public CategoryDao getCategoryDao() {
        return categoryDao;
    }

    public TransferDao getTransferDao() {
        return transferDao;
    }

    public RequestUserDao getRequestDao() {
        return requestUserDao;
    }

    public MenuDao getMenuDao() {
        return menuDao;
    }

    public UserDao getGuestDao() {
        return userDao;
    }

    public OfferDao getOfferDao() {
        return offerDao;
    }


}

//    static {
//        try {
//            instance = new DaoFactory();
//        } catch (DAOException | PoolException e) {
//            throw new RuntimeException("not created", e);
//        }
//    }