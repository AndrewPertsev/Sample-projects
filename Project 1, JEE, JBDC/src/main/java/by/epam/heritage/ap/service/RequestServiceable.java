package by.epam.heritage.ap.service;

import by.epam.heritage.ap.model.RequestUser;

import java.util.List;

public interface RequestServiceable extends Serviceable<RequestUser> {
    /**
     * Changes RequestUser's status "responded" if response has been sent to the user, and vice versa. Default value "false"-"to process".
     *
     * @param isResponded boolean TRUE if response has been sent, and vice versa.
     * @param idRequest   ID of the user's request
     * @return boolean true if the Request status has been changed, else returns false.
     * @throws ServiceException
     */
    boolean setRequestIsRespondedStatus(boolean isResponded, int idRequest) throws ServiceException;

    /**
     * Find request with max ID.
     *
     * @return ID request.
     * @throws ServiceException
     */
    int findMaximumRequestId() throws ServiceException;

    /**
     * Find request with min ID.
     *
     * @return ID request.
     * @throws ServiceException
     */
    int findMinimumRequestId() throws ServiceException;

    /**
     * Finds all requests without response to the guest.
     *
     * @return list user's requests.
     * @throws ServiceException
     */
    List<RequestUser> findUnresponded() throws ServiceException;

    /**
     * Delete entity by id.
     *
     * @param id
     * @return boolean true if the entity has been deleted, else returns false.
     * @throws ServiceException
     */
    boolean deleteByid(int id) throws ServiceException;
}
