package ru.eroonda.shoppinglist.dao;

import ru.eroonda.shoppinglist.util.DBDataGetter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionBuilder {
    public static Connection getConnection() throws SQLException {
        String[] dbData = DBDataGetter.getDBData();

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection connection = DriverManager.getConnection(dbData[0], dbData[1], dbData[2]);
        return connection;
    }

}

