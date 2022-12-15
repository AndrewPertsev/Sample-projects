package by.pertsev.hotel.hiber.dao;

import by.pertsev.hotel.hiber.model.Apartment;

import java.time.LocalDate;
import java.util.List;

public interface ApartmentDao extends HotelEntityDAO<Apartment> {
    /**
     * Find all apartments, suitable for the request parameters
     *
     * @return list of Apartments.
     */
    List<Apartment> findApartmentsSuitableForRequest(LocalDate start, LocalDate end, int category, int quantity);


}
