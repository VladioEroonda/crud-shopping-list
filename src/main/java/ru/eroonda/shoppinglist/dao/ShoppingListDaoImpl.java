package ru.eroonda.shoppinglist.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.eroonda.shoppinglist.entity.ShoppingList;
import ru.eroonda.shoppinglist.handlers.DeleteOnePurchaseHandler;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShoppingListDaoImpl implements ShoppingListDao {

    private final Logger logger = LoggerFactory.getLogger(ShoppingListDaoImpl.class);

    private static final String GET_ALL_LIST =
            "SELECT * FROM shopping_list_1 WHERE session_id = ? ;";

    private static final String ADD_NEW_PURCHASE =
            "INSERT INTO shopping_list_1 (name, count, price, session_id)" +
            " VALUES (?, ? , ?, ?) ;";

    private static final String DELETE_ALL_SESSION_PURCHASES=
            "DELETE FROM shopping_list_1 WHERE session_id= ? ;";

    private static final String DELETE_ONE_CURRENT_PURCHASE=
            "DELETE FROM shopping_list_1 WHERE name = ? AND count = ? AND price = ? AND session_id = ? ;";


    private Connection getConnection() throws SQLException {
        return ConnectionBuilder.getConnection();
    }

    @Override
    public List<ShoppingList> getAllPurchases(String sessionId) {

        List<ShoppingList> resultList = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_ALL_LIST)) {
            statement.setString(1, sessionId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                resultList.add(new ShoppingList(
                        resultSet.getString("name"),
                        resultSet.getInt("count"),
                        resultSet.getDouble("price"),
                        resultSet.getString("session_id")
                ));
            }
        } catch (SQLException exception) {
            System.out.println("<center><h3>Well, something went wrong:(.</h3>" +
                    "<br><a href=\"/index.jsp\">try again</a></center>");
            exception.printStackTrace();
        }

        return resultList;
    }

    @Override
    public void addNewPurchase(String name, String count, String price, String sessionId) {

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(ADD_NEW_PURCHASE)) {
            statement.setString(1, name);
            statement.setInt(2, Integer.parseInt(count));
            statement.setDouble(3, Double.parseDouble(price));
            statement.setString(4, sessionId);
            statement.execute();
        } catch (SQLException exception) {
            System.out.println("<center><h3>Well, something went wrong:(.</h3>" +
                    "<br><a href=\"/index.jsp\">try again</a></center>");
            exception.printStackTrace();
        }
    }

    @Override
    public void deleteAllSessionPurchases(String sessionId) {

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_ALL_SESSION_PURCHASES)) {

            statement.setString(1, sessionId);
            statement.execute();

        } catch (SQLException exception) {
            System.out.println("<center><h3>Well, something went wrong:(.</h3>" +
                    "<br><a href=\"/index.jsp\">try again</a></center>");
            exception.printStackTrace();
        }
    }

    @Override
    public void deleteOneCurrentPurchase(String name, int count, double price, String sessionId) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_ONE_CURRENT_PURCHASE)) {

//            logger.info("BEFORE SQL QUERY:::" +
//                    name.length() + ":"+ name.trim().length() + ":" +
//                    "and S name: \"" + name + "\"" +
//                    "I count: \"" + count + "\"" +
//                    "D price: \"" + price + "\"" +
//                    "Sess ID:\"" + sessionId + "\""
//            );

            statement.setString(1, name);
            statement.setInt(2, count);
            statement.setDouble(3, price);
            statement.setString(4, sessionId);
            boolean execute = statement.execute();

            logger.info("DAO DELETE_ONE AFTER SQL QUERY:" + execute);

        } catch (SQLException exception) {
            System.out.println("<center><h3>Well, something went wrong:(.</h3>" +
                    "<br><a href=\"/index.jsp\">try again</a></center>");
            exception.printStackTrace();
        }
    }

}
