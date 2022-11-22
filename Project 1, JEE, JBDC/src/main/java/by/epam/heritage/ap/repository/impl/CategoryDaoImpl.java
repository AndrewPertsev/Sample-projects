package by.epam.heritage.ap.repository.impl;

import by.epam.heritage.ap.model.Category;
import by.epam.heritage.ap.repository.CategoryDao;
import by.epam.heritage.ap.repository.DAOException;
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

public class CategoryDaoImpl implements CategoryDao {
    private final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final Logger logger = LogManager.getLogger(CategoryDaoImpl.class);
    private static final String GET_CATEGORY_PRICE_BY_CATEGORY_ID = "SELECT category_price FROM category_room WHERE category_id = ?";
    private static final String FIND_CATEGORY_BY_ID = "SELECT category_id, category_name, category_price FROM category_room WHERE category_id = ?";
    private static final String FIND_ALL_CATEGORIES = "SELECT *  FROM category_room ";

    public CategoryDaoImpl() {
    }

    public Category findById(int id) throws DAOException {
        Category category = new Category();

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_CATEGORY_BY_ID)) {
            statement.setString(1, String.valueOf(id));
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                category.setCategory(rs.getInt(1));
                category.setCategoryName(rs.getString(2));
                category.setCategoryPrice(new BigDecimal(rs.getInt(3)));
            }
            return category;

        } catch (SQLException | PoolException e) {
            logger.error("Element does not found ", e);
            throw new DAOException(e);
        }
    }


    @Override
    public BigDecimal getCategoryPrice(int idCategory) throws DAOException {
        BigDecimal categoryPrice = BigDecimal.valueOf(-1);

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_CATEGORY_PRICE_BY_CATEGORY_ID)) {

            statement.setString(1, String.valueOf(idCategory));
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                categoryPrice = new BigDecimal(rs.getInt(1));
            }
            return categoryPrice;

        } catch (SQLException | PoolException e) {
            logger.error("Element does not found ", e);
            throw new DAOException(e);
        }
    }


    @Override
    public List<Category> findAll() throws DAOException {
        List<Category> categories = new ArrayList<>();

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_CATEGORIES);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                Category category = new Category();

                category.setCategory(rs.getInt(1));
                category.setCategoryName(rs.getString(2));
                category.setCategoryPrice(new BigDecimal(rs.getInt(3)));

                categories.add(category);
            }
            return categories;

        } catch (SQLException | PoolException e) {
            logger.error("Element does not found ", e);
            throw new DAOException(e);
        }
    }
}

