package ru.eroonda.shoppinglist.util;

public class DBDataGetter {
    public static String[] getDBData() {
        return getDataFromSystem();
    }

    private static String[] getDataFromSystem() {
        return System.getenv("SHOPPINGLISTDB").split(";");
    }

}
