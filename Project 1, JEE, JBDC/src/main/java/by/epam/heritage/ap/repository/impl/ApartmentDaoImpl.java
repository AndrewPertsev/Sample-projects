package by.epam.heritage.ap.repository.impl;


import by.epam.heritage.ap.model.Apartment;
import by.epam.heritage.ap.model.RequestUser;
import by.epam.heritage.ap.repository.ApartmentDao;
import by.epam.heritage.ap.repository.DAOException;
import by.epam.heritage.ap.repository.connection.ConnectionPool;
import by.epam.heritage.ap.repository.connection.PoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ApartmentDaoImpl implements ApartmentDao {
    private final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final Logger logger = LogManager.getLogger(ApartmentDaoImpl.class);

    private static final String FIND_APARTMENT_SUITABLE_REQUEST = "SELECT DISTINCT apartments.apartment_id  , apartments.category_id, apartments.capacity, apartments.picture , apartments.description FROM hotelappdb.apartments LEFT JOIN timesheet ON apartments.apartment_id=timesheet.apartment_id WHERE (NOT (timesheet.reserved_date >= ? AND timesheet.reserved_date <= ?) OR timesheet.reserved_date IS NULL) AND apartments.category_id=? AND apartments.capacity>=?";
    private static final String FIND_APARTMENT_BY_ID = "SELECT apartment_id , category_id, capacity, picture , description  FROM apartments WHERE apartment_id=?";
    private static final String FIND_ALL_APARTMENT = "SELECT apartment_id, category_id, capacity ,picture , description  FROM apartments";
    private static final String INSERT_NEW_APARTMENT = "INSERT INTO hotelappdb.apartments (category_id, capacity, picture, description, apartment_id) VALUES (?,?,?,?,?)";
    private static final String UPDATE_APARTMENT = "UPDATE hotelappdb.apartments SET  category_id = ?,capacity = ?, picture =?, description = ? WHERE (apartment_id = ?)";
    private static final String DELETE_APARTMENT_BY_ID = "DELETE FROM hotelappdb.apartments WHERE apartments.apartment_id = ?";

    public ApartmentDaoImpl() {
    }


    @Override
    public boolean update(Apartment apartment) throws DAOException {
        boolean done ;

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_APARTMENT)) {

            statement.setInt(1,  apartment.getCategory());
            statement.setInt(2,  apartment.getCapacity());
            statement.setString(3, apartment.getPathToPicture());
            statement.setString(4,apartment.getDescription());
            statement.setInt(5, apartment.getApartmentId());

            statement.executeUpdate();
            done = true;
        } catch (SQLException | PoolException e) {
            logger.error("Element does not found ", e);
            throw new DAOException(e);
        }

        return done;
    }


    @Override
    public List<Apartment> findApartmentsSuitableForRequest(RequestUser requestUser) throws DAOException {
        List<Apartment> suitableApartments = new ArrayList<>(0);

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_APARTMENT_SUITABLE_REQUEST)) {
            statement.setString(1, String.valueOf(requestUser.getStart()));
            statement.setString(2, String.valueOf(requestUser.getEnd()));
            statement.setString(3, String.valueOf(requestUser.getCategory()));
            statement.setString(4, String.valueOf(requestUser.getQuantity()));

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Apartment apartment = new Apartment();

                apartment.setApartmentId(rs.getInt(1));
                apartment.setCategory(rs.getInt(2));
                apartment.setCapacity(rs.getInt(3));
                apartment.setPathToPicture(rs.getString(4));
                apartment.setDescription(rs.getString(5));

                suitableApartments.add(apartment);
            }
            return suitableApartments;

        } catch (SQLException | PoolException e) {
            logger.error("Element does not found ", e);
            throw new DAOException(e);
        }
    }

    @Override
    public List<Apartment> findAll() throws DAOException {
        List<Apartment> rooms = new ArrayList<>(0);

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_APARTMENT);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                Apartment apartment = new Apartment();

                apartment.setApartmentId(rs.getInt(1));
                apartment.setCategory(rs.getInt(2));
                apartment.setCapacity(rs.getInt(3));
                apartment.setPathToPicture(rs.getString(4));
                apartment.setDescription(rs.getString(5));

                rooms.add(apartment);
            }
            return rooms;

        } catch (SQLException | PoolException e) {
            logger.error("Element does not found ", e);
            throw new DAOException(e);
        }
    }

    @Override
    public Apartment findById(int id) throws DAOException {
        Apartment apartment = new Apartment();

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_APARTMENT_BY_ID)) {
            statement.setString(1, String.valueOf(id));
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                apartment.setApartmentId(rs.getInt(1));
                apartment.setCategory(rs.getInt(2));
                apartment.setCapacity(rs.getInt(3));
                apartment.setPathToPicture(rs.getString(4));
                apartment.setDescription(rs.getString(5));
            }
            return apartment;

        } catch (SQLException | PoolException e) {
            logger.error("Element does not found ", e);
            throw new DAOException(e);
        }
    }

    @Override
    public boolean deleteById(int id) throws DAOException {
        boolean done;

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_APARTMENT_BY_ID)) {
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
    public boolean insert(Apartment apartment) throws DAOException {
        boolean done;

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_NEW_APARTMENT)) {

            statement.setInt(1,  apartment.getCategory());
            statement.setInt(2,  apartment.getCapacity());
            statement.setString(3, apartment.getPathToPicture());
            statement.setString(4,apartment.getDescription());
            statement.setInt(5, apartment.getApartmentId());

            statement.executeUpdate();
            done = true;
        } catch (SQLException | PoolException e) {
            logger.error("Element does not found ", e);
            throw new DAOException(e);
        }
        return done;
    }

}

