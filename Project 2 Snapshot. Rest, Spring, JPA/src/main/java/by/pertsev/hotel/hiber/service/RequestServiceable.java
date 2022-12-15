package by.pertsev.hotel.hiber.service;

import by.pertsev.hotel.hiber.dto.RequestUserDto;
import by.pertsev.hotel.hiber.model.RequestUser;

import java.util.List;
import java.util.Optional;

//@Profile("jpa")

public interface RequestServiceable extends Serviceable<RequestUser, RequestUserDto> {

    /**
     * Find request with max ID.
     *
     * @return ID request.
     */
    Optional<RequestUser> findMaximumRequestId();


    /**
     * Finds all requests without response to the guest.
     *
     * @return list user's requests.
     */
    List<RequestUser> findUnresponded();


}
