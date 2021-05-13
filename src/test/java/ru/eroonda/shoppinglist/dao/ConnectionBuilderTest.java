package ru.eroonda.shoppinglist.dao;

import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class ConnectionBuilderTest {

    @Test
    public void getConnection_ShouldReturnNotNullConnectionObject() {

        try(Connection connection = ConnectionBuilder.getConnection()) {

            assertNotNull(connection);

        } catch (SQLException throwables) {
            fail();
        }
    }
}