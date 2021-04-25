package ru.eroonda.shoppinglist.dao;

import ru.eroonda.shoppinglist.entity.ShoppingList;

import java.util.List;

public interface ShoppingListDao {
         List<ShoppingList> getAllPurchases(String sessionId);
         void addNewPurchase(String name, String count, String price, String sessionId);
         void deleteAllSessionPurchases(String sessionId);
}
