package by.epam.heritage.ap.repository.impl;


import by.epam.heritage.ap.model.Menu;
import by.epam.heritage.ap.repository.DAOException;
import by.epam.heritage.ap.repository.MenuDao;
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

public class MenuDaoImpl implements MenuDao {
    private final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final Logger logger = LogManager.getLogger(MenuDaoImpl.class);

    private static final String FIND_MENU_BY_ID = "SELECT menu_id, menu, price_menu  FROM menu WHERE menu_id = ?";
    private static final String FIND_ALL_MENU = "SELECT * FROM menu";

    public MenuDaoImpl() {
    }

    @Override
    public Menu findById(int id) throws DAOException {
        Menu menu = new Menu();

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_MENU_BY_ID)) {
            statement.setString(1,  String.valueOf(id));
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                menu.setMenu(rs.getInt(1));
                menu.setMenuMode(rs.getString(2));
                menu.setPrice(new BigDecimal(rs.getInt(3)));
            }
            return menu;

        } catch (SQLException | PoolException e) {
            logger.error("Element does not found ", e);
            throw new DAOException(e);
        }
    }

    @Override
    public List <Menu> findAll() throws DAOException {
        List<Menu> menus = new ArrayList<>();

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_MENU);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                Menu menu = new Menu();

                menu.setMenu(rs.getInt(1));
                menu.setMenuMode(rs.getString(2));
                menu.setPrice(new BigDecimal(rs.getInt(3)));

                menus.add(menu);
            }
            return menus;

        } catch (SQLException | PoolException e) {
            logger.error("Element does not found ", e);
            throw new DAOException(e);
        }
    }


}
