package by.epam.heritage.ap.repository.impl;

import by.epam.heritage.ap.model.Transfer;
import by.epam.heritage.ap.repository.DAOException;
import by.epam.heritage.ap.repository.TransferDao;
import by.epam.heritage.ap.repository.connection.ConnectionPool;
import by.epam.heritage.ap.repository.connection.PoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransferDaoImpl implements TransferDao {
    private final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final Logger logger = LogManager.getLogger(TransferDaoImpl.class);

    private static final String FIND_TRANSFER_BY_ID = "SELECT transfer_id, transfer, price_1_km  FROM transfer WHERE transfer_id = ?";
    private static final String FIND_ALL_TRANSFERS = "SELECT *  FROM transfer ";


    public TransferDaoImpl() {
    }

    @Override
    public Transfer findById(int id) throws DAOException {
        Transfer transfer = new Transfer();

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_TRANSFER_BY_ID)){

            statement.setString(1, String.valueOf(id));
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                transfer.setTransfer(rs.getInt(1));
                transfer.setTransferMode(rs.getString(2));
                transfer.setPrice(new BigDecimal(rs.getInt(3)));
            }
            return transfer;

        } catch (SQLException | PoolException e) {
            logger.error("Element does not found ", e);
            throw new DAOException(e);
        }
    }

    @Override
    public List<Transfer> findAll() throws DAOException {
        List<Transfer> transfers = new ArrayList<>();

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_TRANSFERS);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                Transfer transfer = new Transfer();

                transfer.setTransfer(rs.getInt(1));
                transfer.setTransferMode(rs.getString(2));
                transfer.setPrice(new BigDecimal(rs.getInt(3)));

                transfers.add(transfer);
            }
            return transfers;

        } catch (SQLException | PoolException e) {
            logger.error("Element does not found ", e);
            throw new DAOException(e);
        }
    }

}
