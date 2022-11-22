package by.epam.heritage.ap.service;

import by.epam.heritage.ap.model.Timesheet;

import java.time.LocalDate;

public interface TimesheetServiceable extends Serviceable<Timesheet> {

    /**
     * Book apartment for certain date.
     *
     * @param apartmentId  id of the apartment.
     * @param bookedFrom   - check-in date.
     * @param bookedBefore - check-out date.
     * @return boolean true if the apartment has been book, else returns false.
     * @throws ServiceException
     */
    boolean reserveConfirmedDaysByOffer(int apartmentId, LocalDate bookedFrom, LocalDate bookedBefore) throws ServiceException;
}
