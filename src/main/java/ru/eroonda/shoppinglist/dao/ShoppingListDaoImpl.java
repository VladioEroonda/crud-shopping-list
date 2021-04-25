package ru.eroonda.shoppinglist.dao;

import ru.eroonda.shoppinglist.entity.ShoppingList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShoppingListDaoImpl implements ShoppingListDao {

    private static final String GET_ALL_LIST =
            "SELECT * FROM shopping_list_1";

    private static final String ADD_NEW_PURCHASE =
            "INSERT INTO shopping_list_1 (name, count, price, session_id)" +
            " VALUES (?, ? , ?, ?);";

    private Connection getConnection() throws SQLException {
        return ConnectionBuilder.getConnection();
    }

    @Override
    public List<ShoppingList> getAllPurchases() {

        List<ShoppingList> resultList = new ArrayList<>();

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(GET_ALL_LIST);

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

}
