package ru.eroonda.shoppinglist.util;

/**
 * Считывает переменную окружения(см. переменные среды) SHOPPINGLISTDB
 * в которой указаны через точку с запятой адрес бд, пароль и логин т.е
 * jdbc:postgresql://localhost:5432/bdname;bdlogin;bdpassword
 * и всё это разбивает на части и возвращает массивом строк
 */

public class DBDataGetter {

    public static String[] getDBData() {
        return getDataFromSystem();
    }

    private static String[] getDataFromSystem() {
        return System.getenv("SHOPPINGLISTDB").split(";");
    }

}
