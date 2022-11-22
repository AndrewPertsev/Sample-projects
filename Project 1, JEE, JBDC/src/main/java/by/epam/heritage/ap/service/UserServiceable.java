package by.epam.heritage.ap.service;

import by.epam.heritage.ap.model.User;

public interface UserServiceable extends Serviceable<User> {
    /**
     * Checks if user's login and pasword are valid.
     *
     * @param login           user's login from Login form.
     * @param passwordToCheck user's password from Login form.
     * @return User with valid login and password.
     * @throws ServiceException
     */
    User checkGuestAuthorization(String login, String passwordToCheck) throws ServiceException;


}
