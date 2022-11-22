package by.epam.heritage.ap.repository;


import by.epam.heritage.ap.model.User;

public interface UserDao extends BaseDAO<User> {
    /**
     * Finds user by login.
     *
     * @param login
     * @return user with certain login
     * @throws DAOException
     */
    User findByName(String login) throws DAOException;

    /**
     * Updates user's data.
     *
     * @param entity
     * @return boolean true if the new user has been updated, else returns false.
     * @throws DAOException
     */
    boolean update(User entity) throws DAOException;

    /**
     * Adds the new user.
     *
     * @param entity
     * @return boolean true if the new user has been added, else returns false.
     * @throws DAOException
     */
    boolean insert(User entity) throws DAOException;

}
