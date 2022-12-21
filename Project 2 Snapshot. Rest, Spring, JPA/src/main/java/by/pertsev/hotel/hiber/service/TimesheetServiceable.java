package by.pertsev.hotel.hiber.service;

import by.pertsev.hotel.hiber.model.RequestUser;
import by.pertsev.hotel.hiber.model.Timesheet;
import by.pertsev.hotel.hiber.model.dto.TimesheetDto;

//@Profile("jpa")

public interface TimesheetServiceable extends Serviceable<Timesheet, TimesheetDto> {

    /**
     * Book apartment for certain date.
     *
     * @param apartmentId id of the apartment.
     * @return boolean true if the apartment has been book, else returns false.
     */
    boolean reserveConfirmedDaysByOffer(int apartmentId, RequestUser requestUser);/////////////////////////////
}
