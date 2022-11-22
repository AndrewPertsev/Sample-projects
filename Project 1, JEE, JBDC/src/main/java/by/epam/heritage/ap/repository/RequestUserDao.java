package by.epam.heritage.ap.repository;

import by.epam.heritage.ap.model.RequestUser;

import java.util.List;

public interface RequestUserDao extends BaseDAO<RequestUser> {

    /**
     * Changes RequestUser's status "responded" if response has been sent to the user, and vice versa. Default value "false"-"to process".
     *
     * @param isResponded boolean TRUE if response has been sent, and vice versa.
     * @param idRequest   ID of the user's request
     * @return boolean true if the Request status has been changed, else returns false.
     * @throws DAOException
     */
    boolean setRequestIsRespondedStatus(boolean isResponded, int idRequest) throws DAOException;

    /**
     * Finds all requests without response to the guest.
     *
     * @return list user's requests.
     * @throws DAOException
     */
    List<RequestUser> findUnresponded() throws DAOException;

    /**
     * Find request with min ID.
     *
     * @return ID request.
     * @throws DAOException
     */
    int findMinimumRequestId() throws DAOException;

    /**
     * Find request with max ID.
     *
     * @return ID request.
     * @throws DAOException
     */
    int findMaximumRequestId() throws DAOException;


    /**
     * Deletes request by ID.
     *
     * @param id of the user's request.
     * @return boolean true if the new Request has been added, else returns false.
     * @throws DAOException
     */
    boolean deleteByid(int id) throws DAOException;

    /**
     * Updates user's request.
     *
     * @param entity
     * @return boolean true if the new Request has been added, else returns false.
     * @throws DAOException
     */
    boolean update(RequestUser entity) throws DAOException;

    /**
     * Adds new user's request.
     *
     * @param entity
     * @return boolean true if the new Request has been added, else returns false.
     * @throws DAOException
     */
    boolean insert(RequestUser entity) throws DAOException;

}
