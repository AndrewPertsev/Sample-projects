package by.epam.heritage.ap.repository.impl;


import by.epam.heritage.ap.model.Timesheet;
import by.epam.heritage.ap.repository.DAOException;
import by.epam.heritage.ap.repository.TimesheetDao;
import by.epam.heritage.ap.repository.connection.ConnectionPool;
import by.epam.heritage.ap.repository.connection.PoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static by.epam.heritage.ap.controller.ConstantsParametersAndAttributes.IS_RESERVED_APARTMENT_TRUE;

public class TimesheetDaoImpl implements TimesheetDao {
    private final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final Logger logger = LogManager.getLogger(TimesheetDaoImpl.class);

    private final static String INSERT_INTO_TIMESHEET_CONFIRMED_DAYS = "INSERT INTO hotelappdb.timesheet SET apartment_id=? , isreserved=?, reserved_date=?";
    private final static String FIND_TIMESHEET_BY_ID = "SELECT id_timesheet, apartment_id, reserved_date, isreserved FROM timesheet WHERE id_timesheet = ?";
    private final static String FIND_ALL_TIMESHEET = "SELECT id_timesheet, apartment_id, reserved_date, isreserved FROM timesheet ";
    private final static String UPDATE_TIMESHEET = "UPDATE id_timesheet SET apartment_id=?, reserved_date=?, isreserved=? WHERE id_timesheet=? ";
    private final static String ADD_TIMESHEET = "INSERT INTO hotelappdb.timesheet (apartment_id, reserved_date, isreserved) VALUES (?,?,?,?)";

    public TimesheetDaoImpl() {
    }

    @Override
    public boolean reserveConfirmedDaysByOffer(int apartmentId, LocalDate bookFrom, LocalDate bookedBefore) throws DAOException {
        boolean done;

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_INTO_TIMESHEET_CONFIRMED_DAYS)) {

            Duration duration = Duration.between(bookFrom.atStartOfDay(), bookedBefore.atStartOfDay());
            long durationDays = duration.toDays();
            for (int i = 0; i < durationDays; i++) {
                bookFrom = bookFrom.plusDays(1);

                statement.setInt(1, apartmentId);
                statement.setBoolean(2, IS_RESERVED_APARTMENT_TRUE);
                statement.setDate(3, java.sql.Date.valueOf(bookFrom));

                statement.executeUpdate();
            }
            done = true;
        } catch (SQLException | PoolException e) {
            logger.error("Element does not found ", e);
            throw new DAOException(e);

        }
        return done;
    }


    @Override
    public Timesheet findById(int id) throws DAOException {
        Timesheet timesheet = new Timesheet();

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_TIMESHEET_BY_ID)) {
            statement.setString(1, String.valueOf(id));
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                timesheet.setTimesheetId(rs.getInt(1));
                timesheet.setApartmentId(rs.getInt(2));
                timesheet.setReservedDate(LocalDate.parse(rs.getString(3)));
                timesheet.setIsReserved(rs.getBoolean(4));
            }
            return timesheet;

        } catch (SQLException | PoolException e) {
            logger.error("Element does not found ", e);
            throw new DAOException(e);
        }
    }

    @Override
    public List<Timesheet> findAll() throws DAOException {
        List<Timesheet> timesheets = new ArrayList<>();

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_TIMESHEET);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                Timesheet timesheet = new Timesheet();

                timesheet.setTimesheetId(rs.getInt(1));
                timesheet.setApartmentId(rs.getInt(2));
                timesheet.setReservedDate(LocalDate.parse(rs.getString(3)));
                timesheet.setIsReserved(rs.getBoolean(4));

                timesheets.add(timesheet);
            }
            return timesheets;

        } catch (SQLException | PoolException e) {
            logger.error("Element does not found ", e);
            throw new DAOException(e);
        }
    }

    @Override
    public boolean update(Timesheet entity) throws DAOException {
        boolean done ;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_TIMESHEET)) {
            statement.setInt(1, entity.getApartmentId());
            statement.setDate(2, Date.valueOf(entity.getReservedDate()));
            statement.setBoolean(3, entity.getIsReserved());
            statement.setInt(4, entity.getTimesheetId());

            statement.executeUpdate();
            done = true;
        } catch (SQLException | PoolException e) {
            logger.error("Element does not updated ", e);
            throw new DAOException(e);
        }
        return done;
    }


    @Override
    public boolean insert(Timesheet entity) throws DAOException {
        boolean done;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(ADD_TIMESHEET)) {
            statement.setInt(1, entity.getApartmentId());
            statement.setDate(2, Date.valueOf(entity.getReservedDate()));
            statement.setBoolean(3, entity.getIsReserved());

            statement.executeUpdate();
            done = true;
        } catch (SQLException | PoolException e) {
            logger.error("Element does not found ", e);
            throw new DAOException(e);
        }
        return done;
    }

}
