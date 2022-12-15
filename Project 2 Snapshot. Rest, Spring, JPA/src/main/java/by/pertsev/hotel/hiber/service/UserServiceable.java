package by.pertsev.hotel.hiber.service;

import by.pertsev.hotel.hiber.dto.UserDto;
import by.pertsev.hotel.hiber.model.User;

import java.util.Optional;

//@Profile("data")

public interface UserServiceable extends Serviceable<User, UserDto> {

    /**
     * Find user by login
     *
     * @param login
     * @return
     */
    Optional<User> findByLogin(String login);
}
