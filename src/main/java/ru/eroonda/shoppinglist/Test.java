package ru.eroonda.shoppinglist;

import ru.eroonda.shoppinglist.dao.ShoppingListDao;
import ru.eroonda.shoppinglist.dao.ShoppingListDaoImpl;
import ru.eroonda.shoppinglist.entity.ShoppingList;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        ShoppingListDao dao = new ShoppingListDaoImpl();
//        dao.addNewPurchase("Beer Bekker nefiltr. 0.5","5","60.0");
        List<ShoppingList> allPurchases = dao.getAllPurchases();
//        allPurchases.forEach(l -> System.out.println(l.getId()));

    }
}
