package by.pertsev.hotel.hiber.dao;

import by.pertsev.hotel.hiber.model.User;

import java.util.Optional;

public interface UserDao extends HotelEntityDAO<User> {

    /**
     * Finds user by login.
     *
     * @param login
     * @return user with certain login
     */
    Optional<User> findByLogin(String login);

}
