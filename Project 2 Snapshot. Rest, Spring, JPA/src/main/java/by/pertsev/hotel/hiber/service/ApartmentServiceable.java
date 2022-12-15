package by.pertsev.hotel.hiber.service;

import by.pertsev.hotel.hiber.dto.ApartmentDto;
import by.pertsev.hotel.hiber.model.Apartment;

import java.util.List;

//@Profile("jpa")

public interface ApartmentServiceable extends Serviceable<Apartment, ApartmentDto> {

    /**
     * Finds apartment, suitable for the user's request.
     *
     * @param idRequestUser
     * @return Apartments list.
     */
    List<Apartment> findApartmentsAvailableForRequest(int idRequestUser);


    public void evictCache();
}
