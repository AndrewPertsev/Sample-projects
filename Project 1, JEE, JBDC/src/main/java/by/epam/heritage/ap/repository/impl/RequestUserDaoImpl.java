package by.epam.heritage.ap.repository.impl;

import by.epam.heritage.ap.controller.ConstantsParametersAndAttributes;
import by.epam.heritage.ap.model.RequestUser;
import by.epam.heritage.ap.repository.DAOException;
import by.epam.heritage.ap.repository.RequestUserDao;
import by.epam.heritage.ap.repository.connection.ConnectionPool;
import by.epam.heritage.ap.repository.connection.PoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RequestUserDaoImpl implements RequestUserDao {
    private final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final Logger logger = LogManager.getLogger(RequestUserDaoImpl.class);

    private static final String DELETE_REQUEST_BY_ID = "DELETE FROM hotelappdb.request WHERE request.request_id = ?";
    private static final String INSERT_NEW_REQUEST = "INSERT INTO hotelappdb.request (request.user_id, request.menu_id , request.transfer_id, request.quantity_persons, request.category_id, distance, date_in, date_out, date_request,  responded) VALUES (?,?,?, ?,?,? ,?,?,?, ?)";
    private static final String FIND_MAXIMUM_REQUEST_ID = "SELECT MAX(request_id) FROM hotelappdb.request ";
    private static final String FIND_MINIMUM_REQUEST_ID = "SELECT MIN(request_id) FROM hotelappdb.request ";
    private final static String FIND_ALL_REQUESTS = "SELECT request_id,user_id ,menu_id ,transfer_id ,category_id,quantity_persons,date_in ,date_out ,distance ,date_request , responded FROM request ";
    private final static String FIND_UNRESPONDED_REQUESTS = "SELECT request_id,user_id ,menu_id ,transfer_id ,category_id,quantity_persons,date_in ,date_out ,distance ,date_request , responded FROM request WHERE responded=false ORDER BY date_request DESC LIMIT 7";
    private final static String FIND_REQUEST_BY_ID = "SELECT request_id,user_id ,menu_id ,transfer_id ,category_id,quantity_persons,date_in ,date_out ,distance ,date_request , responded FROM request WHERE request_id = ?";
    private final static String UPDATE_REQUEST_BY_ID = "UPDATE hotelappdb.request SET user_id =? , menu_id = ?, transfer_id = ?, quantity_persons = ?, category_id = ? ,distance= ?  ,date_in = ?, date_out = ?, date_request= ? , responded= ? WHERE (request_id = ?)";
    private static final String UPDATE_REQUEST_IS_RESPONDED_STATUS = "UPDATE hotelappdb.request SET responded = ? WHERE (request_id = ?)";

    public RequestUserDaoImpl() {
    }

    @Override
    public List<RequestUser> findUnresponded() throws DAOException {
        List<RequestUser> requestUsers = new ArrayList<>(0);

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_UNRESPONDED_REQUESTS); ResultSet rs = statement.executeQuery()
        ) {
            while (rs.next()) {
                RequestUser requestUser = new RequestUser();

                requestUser.setRequestId(rs.getInt(1));
                requestUser.setUserId(rs.getInt(2));
                requestUser.setMenu(rs.getInt(3));
                requestUser.setTransfer(rs.getInt(4));
                requestUser.setCategory(rs.getInt(5));
                requestUser.setQuantity(rs.getInt(6));
                requestUser.setStart(LocalDate.parse(rs.getString(7)));
                requestUser.setEnd(LocalDate.parse(rs.getString(8)));
                requestUser.setDistance(rs.getInt(9));
                requestUser.setDateRequest(LocalDate.parse(rs.getString(10)));
                requestUser.setResponded(rs.getBoolean(11));

                requestUsers.add(requestUser);
            }
            return requestUsers;

        } catch (SQLException | PoolException e) {
            logger.error("Element does not found ", e);
            throw new DAOException(e);

        }
    }

    @Override
    public boolean update(RequestUser requestUser) throws DAOException {
        boolean done;

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_REQUEST_BY_ID)) {

            statement.setInt(1, requestUser.getUserId());
            statement.setInt(2, requestUser.getMenu());
            statement.setInt(3, requestUser.getTransfer());
            statement.setInt(4, requestUser.getQuantity());
            statement.setInt(5, requestUser.getCategory());
            statement.setInt(6, ConstantsParametersAndAttributes.DEFAULT_DISTANCE);
            statement.setDate(7, Date.valueOf(requestUser.getStart()));
            statement.setDate(8, Date.valueOf(requestUser.getEnd()));
            statement.setDate(9, Date.valueOf(LocalDate.now()));
            statement.setBoolean(10, ConstantsParametersAndAttributes.DEFAULT_IS_RESPONDED);
            statement.setInt(11, requestUser.getRequestId());

            statement.executeUpdate();
            done = true;
        } catch (SQLException | PoolException e) {
            logger.error("Element does not found ", e);
            throw new DAOException(e);

        }
        return done;
    }


    @Override
    public boolean insert(RequestUser requestUser) throws DAOException {
        boolean done;

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_NEW_REQUEST)) {

            statement.setInt(1, requestUser.getUserId());
            statement.setInt(2, requestUser.getMenu());
            statement.setInt(3, requestUser.getTransfer());
            statement.setInt(4, requestUser.getQuantity());
            statement.setInt(5, requestUser.getCategory());
            statement.setInt(6, ConstantsParametersAndAttributes.DEFAULT_DISTANCE);
            statement.setDate(7, Date.valueOf(requestUser.getStart()));
            statement.setDate(8, Date.valueOf(requestUser.getEnd()));
            statement.setDate(9, Date.valueOf(LocalDate.now()));
            statement.setBoolean(10, ConstantsParametersAndAttributes.DEFAULT_IS_RESPONDED);

            statement.executeUpdate();
            done = true;

        } catch (SQLException | PoolException e) {
            logger.error("Element does not found ", e);
            throw new DAOException(e);

        }
        return done;
    }


    @Override
    public RequestUser findById(int id) throws DAOException {
        RequestUser requestUser = new RequestUser();

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_REQUEST_BY_ID)) {
            statement.setString(1, String.valueOf(id));
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                requestUser.setRequestId(rs.getInt(1));
                requestUser.setUserId(rs.getInt(2));
                requestUser.setMenu(rs.getInt(3));
                requestUser.setTransfer(rs.getInt(4));
                requestUser.setCategory(rs.getInt(5));
                requestUser.setQuantity(rs.getInt(6));
                requestUser.setStart(LocalDate.parse(rs.getString(7)));
                requestUser.setEnd(LocalDate.parse(rs.getString(8)));
                requestUser.setDistance(rs.getInt(9));
                requestUser.setDateRequest(LocalDate.parse(rs.getString(10)));
                requestUser.setResponded(rs.getBoolean(11));

            }
            return requestUser;

        } catch (SQLException | PoolException e) {
            logger.error("Element does not found ", e);
            throw new DAOException(e);

        }
    }

    @Override
    public List<RequestUser> findAll() throws DAOException {
        List<RequestUser> requestUsers = new ArrayList<>(0);

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_REQUESTS); ResultSet rs = statement.executeQuery()
        ) {
            while (rs.next()) {
                RequestUser requestUser = new RequestUser();

                requestUser.setRequestId(rs.getInt(1));
                requestUser.setUserId(rs.getInt(2));
                requestUser.setMenu(rs.getInt(3));
                requestUser.setTransfer(rs.getInt(4));
                requestUser.setCategory(rs.getInt(5));
                requestUser.setQuantity(rs.getInt(6));
                requestUser.setStart(LocalDate.parse(rs.getString(7)));
                requestUser.setEnd(LocalDate.parse(rs.getString(8)));
                requestUser.setDistance(rs.getInt(9));
                requestUser.setDateRequest(LocalDate.parse(rs.getString(10)));
                requestUser.setResponded(rs.getBoolean(11));

                requestUsers.add(requestUser);
            }
            return requestUsers;

        } catch (SQLException | PoolException e) {
            logger.error("Element does not found ", e);
            throw new DAOException(e);

        }
    }


    @Override
    public boolean setRequestIsRespondedStatus(boolean isResponded, int idRequest) throws DAOException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_REQUEST_IS_RESPONDED_STATUS)) {

            statement.setBoolean(1, isResponded);
            statement.setInt(2, idRequest);
            statement.executeUpdate();

        } catch (SQLException | PoolException e) {
            logger.error("Element does not found ", e);
            throw new DAOException(e);

        }
        return isResponded;
    }


    @Override
    public boolean deleteByid(int id) throws DAOException {
        boolean done;

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_REQUEST_BY_ID)) {
            statement.setInt(1, id);
            statement.executeUpdate();
            done = true;
        } catch (SQLException | PoolException e) {
            logger.error("Element does not found ", e);
            throw new DAOException(e);
        }
        return done;
    }


    @Override
    public int findMaximumRequestId() throws DAOException {
        int idMax = -1;

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_MAXIMUM_REQUEST_ID);
             ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                idMax = rs.getInt(1);
            }
            return idMax;

        } catch (SQLException | PoolException e) {
            logger.error("Element does not found ", e);
            throw new DAOException(e);

        }
    }
    @Override
    public int findMinimumRequestId() throws DAOException {
        int idMax = -1;

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_MINIMUM_REQUEST_ID);
             ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                idMax = rs.getInt(1);
            }
            return idMax;

        } catch (SQLException | PoolException e) {
            logger.error("Element does not found ", e);
            throw new DAOException(e);

        }
    }
}

