package by.epam.heritage.ap.service.impl;

import by.epam.heritage.ap.model.Timesheet;
import by.epam.heritage.ap.repository.DAOException;
import by.epam.heritage.ap.repository.DaoFactory;
import by.epam.heritage.ap.repository.TimesheetDao;
import by.epam.heritage.ap.service.ServiceException;
import by.epam.heritage.ap.service.TimesheetServiceable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.List;

public class TimesheetServiceImpl implements TimesheetServiceable {
    private static final Logger logger = LogManager.getLogger(TimesheetServiceImpl.class);
    TimesheetDao timesheetDao = DaoFactory.getInstance().getTimesheetDao();

    @Override
    public boolean reserveConfirmedDaysByOffer(int apartmentId, LocalDate bookedFrom, LocalDate bookedBefore) throws ServiceException {
        boolean done;
        try {
            done = timesheetDao.reserveConfirmedDaysByOffer(apartmentId, bookedFrom, bookedBefore);
        } catch (DAOException e) {
            logger.error("Service can't reserve apartment in the timesheet ", e);
            throw new ServiceException(e);
        }
        return done;
    }


    @Override
    public List findAll() throws ServiceException {
        List<Timesheet> timesheets ;
        try {
            timesheets = timesheetDao.findAll();
        } catch (DAOException e) {
            logger.error("Service can't reserve apartment in the timesheet ", e);
            throw new ServiceException(e);
        }
        return timesheets;
    }

    @Override
    public Timesheet findById(int id) throws ServiceException {
        Timesheet timesheet;
        try {
            timesheet = timesheetDao.findById(id);
        } catch (DAOException e) {
            logger.error("Service can't reserve apartment in the timesheet ", e);
            throw new ServiceException(e);
        }
        return timesheet;
    }

    @Override
    public boolean add(Timesheet entity) throws ServiceException {
        boolean done;
        try {
            done = timesheetDao.insert(entity);
        } catch (DAOException e) {
            logger.error("Service can't update apartment in the timesheet ", e);
            throw new ServiceException(e);
        }
        return done;
    }


    @Override
    public boolean update(Timesheet entity) throws ServiceException {
        boolean done;
        try {
            done = timesheetDao.update(entity);
        } catch (DAOException e) {
            logger.error("Service can't update apartment in the timesheet ", e);
            throw new ServiceException(e);
        }
        return done;
    }
}
