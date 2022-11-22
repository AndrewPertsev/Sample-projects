package by.epam.heritage.ap.repository;

import by.epam.heritage.ap.model.Timesheet;

import java.time.LocalDate;

/**
 * Interface for the Timesheet.
 */
public interface TimesheetDao extends BaseDAO<Timesheet> {

    /**
     * Book apartment for certain date.
     *
     * @param apartmentId  id of the apartment.
     * @param bookedFrom   - check-in date.
     * @param bookedBefore - check-out date.
     * @return boolean true if the apartment has been book, else returns false.
     * @throws DAOException
     */
    boolean reserveConfirmedDaysByOffer(int apartmentId, LocalDate bookedFrom, LocalDate bookedBefore) throws DAOException;

    /**
     * Updates timesheet point.
     *
     * @param entity
     * @return boolean true if the new Request has been added, else returns false.
     * @throws DAOException
     */
    boolean update(Timesheet entity) throws DAOException;

    /**
     * Adds new timesheet point.
     *
     * @param entity
     * @return boolean true if the new Request has been added, else returns false.
     * @throws DAOException
     */
    boolean insert(Timesheet entity) throws DAOException;

}
