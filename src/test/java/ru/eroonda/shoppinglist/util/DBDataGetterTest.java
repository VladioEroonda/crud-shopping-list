package ru.eroonda.shoppinglist.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class DBDataGetterTest {

    @Test
    public void getDBData_ShouldReturnNotNullStringArray() {
        String[] array =  DBDataGetter.getDBData();
        assertNotNull("Cant read global variable", array);
    }
}