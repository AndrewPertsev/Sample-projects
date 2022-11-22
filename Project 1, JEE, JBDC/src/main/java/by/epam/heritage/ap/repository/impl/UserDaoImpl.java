package by.epam.heritage.ap.repository.impl;

import by.epam.heritage.ap.controller.ConstantsParametersAndAttributes;
import by.epam.heritage.ap.model.User;
import by.epam.heritage.ap.repository.DAOException;
import by.epam.heritage.ap.repository.UserDao;
import by.epam.heritage.ap.repository.connection.ConnectionPool;
import by.epam.heritage.ap.repository.connection.PoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final Logger logger = LogManager.getLogger(UserDaoImpl.class);

    private static final String ADD_GUEST_INTO_USERS = "INSERT INTO hotelappdb.users (role_id,login, password, user_name, surname, email, tel, passport_number, country, comments, vip_status, nongrata_status) VALUES (?,?,?, ?,?,? ,?,?,?, ?,?,?)";
    private static final String FIND_GUEST_BY_LOGIN = "SELECT users.user_id ,role_id ,login ,user_name,surname ,email ,tel ,passport_number ,country ,vip_status ,nongrata_status ,comments ,password FROM users WHERE login = ? ";
    private static final String FIND_GUEST_BY_ID = "SELECT users.user_id ,role_id ,login ,user_name,surname ,email ,tel ,passport_number ,country ,vip_status ,nongrata_status ,comments ,password FROM users WHERE users.user_id = ?";
    private static final String FIND_ALL_GUEST = "SELECT users.user_id ,role_id ,login ,user_name,surname ,email ,tel ,passport_number ,country ,vip_status ,nongrata_status ,comments FROM hotelappdb.users ";
    private static final String UPDATE_GUEST = "UPDATE hotelappdb.users SET role_id =?, user_name = ?,surname = ?,email = ?, tel = ?, comments = ?, vip_status = ?, nongrata_status = ? WHERE (user_id = ?);";
    private static final String UPDATE_GUEST_PASSWORD_IN_USERS = "UPDATE hotelappdb.users SET password= ? WHERE (user_id = ?)";

    public UserDaoImpl() {
    }

    @Override
    public List<User> findAll() throws DAOException {
        List<User> guests = new ArrayList<>();

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_GUEST);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                User guest = new User();
                guest.setUserId(rs.getInt(1));
                guest.setRoleId(rs.getInt(2));
                guest.setLogin(rs.getString(3));
                guest.setName(rs.getString(4));
                guest.setSurname(rs.getString(5));
                guest.setEmail(rs.getString(6));
                guest.setTel(rs.getString(7));
                guest.setPassport(rs.getInt(8));
                guest.setCountry(rs.getString(9));
                guest.setVip(rs.getBoolean(10));
                guest.setNonGrata(rs.getBoolean(11));
                guest.setComment(rs.getString(12));

                guests.add(guest);
            }
            return guests;

        } catch (SQLException | PoolException e) {
            logger.error("Element does not found ", e);
            throw new DAOException(e);
        }
    }


    @Override
    public boolean update(User guest) throws DAOException {
        boolean done;

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_GUEST)) {

            statement.setInt(1, guest.getRoleId());
            statement.setString(2, guest.getName());
            statement.setString(3, guest.getSurname());
            statement.setString(4, guest.getEmail());
            statement.setString(5, guest.getTel());
            statement.setString(6, guest.getComment());
            statement.setBoolean(7, guest.isVip());
            statement.setBoolean(8, guest.isNonGrata());
            statement.setInt(9, guest.getUserId());

            statement.executeUpdate();
            done = true;
        } catch (SQLException | PoolException e) {
            logger.error("Element does not found ", e);
            throw new DAOException(e);
        }
        return done;

    }


    @Override
    public boolean insert(User guest) throws DAOException {
        boolean done;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(ADD_GUEST_INTO_USERS)) {

            statement.setInt(1, ConstantsParametersAndAttributes.DEFAULT_ROLE_ID);
            statement.setString(2, guest.getLogin());
            statement.setString(3, guest.getPassword());
            statement.setString(4, guest.getName());
            statement.setString(5, guest.getSurname());
            statement.setString(6, guest.getEmail());
            statement.setString(7, guest.getTel());
            statement.setInt(8, guest.getPassport());
            statement.setString(9, guest.getCountry());
            statement.setString(10, ConstantsParametersAndAttributes.DEFAULT_COMMENT);
            statement.setBoolean(11, ConstantsParametersAndAttributes.DEFAULT_VIP_STATUS);
            statement.setBoolean(12, ConstantsParametersAndAttributes.DEFAULT_NONGRATA_STATUS);
//            statement.setBoolean(13, user.isActive());

            statement.executeUpdate();
            done = true;
        } catch (SQLException | PoolException e) {
            logger.error("Element does not found ", e);
            throw new DAOException(e);
        }
        return done;
    }

    @Override
    public User findByName(String login) throws DAOException {
        User guest = new User();

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_GUEST_BY_LOGIN)) {

            statement.setString(1, login);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {

                guest.setUserId(rs.getInt(1));
                guest.setRoleId(rs.getInt(2));
                guest.setLogin(rs.getString(3));
                guest.setName(rs.getString(4));
                guest.setSurname(rs.getString(5));
                guest.setEmail(rs.getString(6));
                guest.setTel(rs.getString(7));
                guest.setPassport(rs.getInt(8));
                guest.setCountry(rs.getString(9));
                guest.setVip(rs.getBoolean(10));
                guest.setNonGrata(rs.getBoolean(11));
                guest.setComment(rs.getString(12));
                guest.setPassword(rs.getString(13));
            }
            return guest;

        } catch (SQLException | PoolException e) {
            logger.error("Element does not found ", e);
            throw new DAOException(e);
        }
    }

    @Override
    public User findById(int id) throws DAOException {
        User guest = new User();

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_GUEST_BY_ID)) {

            statement.setString(1, String.valueOf(id));
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                guest.setUserId(rs.getInt(1));
                guest.setRoleId(rs.getInt(2));
                guest.setLogin(rs.getString(3));
                guest.setName(rs.getString(4));
                guest.setSurname(rs.getString(5));
                guest.setEmail(rs.getString(6));
                guest.setTel(rs.getString(7));
                guest.setPassport(rs.getInt(8));
                guest.setCountry(rs.getString(9));
                guest.setVip(rs.getBoolean(10));
                guest.setNonGrata(rs.getBoolean(11));
                guest.setComment(rs.getString(12));
                guest.setPassword(rs.getString(13));
            }
            return guest;

        } catch (SQLException | PoolException e) {
            logger.error("Element does not found ", e);
            throw new DAOException(e);

        }
    }

    public boolean updatePassword(int idGuest, String newPassword) throws DAOException {
        boolean done;
        String hashPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_GUEST_PASSWORD_IN_USERS)) {

            statement.setString(1, hashPassword);
            statement.setInt(2, idGuest);
            statement.executeUpdate();
            done = true;
            return done;
        } catch (SQLException | PoolException e) {
            logger.error("Element does not found ", e);
            throw new DAOException(e);

        }
    }
}


