package by.epam.heritage.ap.repository;

import by.epam.heritage.ap.model.Category;

import java.math.BigDecimal;

public interface CategoryDao extends BaseDAO<Category> {
    /**
     * Calculates price of the apartment category by ID category,.
     *
     * @param idCategory
     * @return price of the apartment category level in BigDecimal format.
     * @throws DAOException
     */
    BigDecimal getCategoryPrice(int idCategory) throws DAOException;
}
