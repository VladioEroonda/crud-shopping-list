package ru.eroonda.shoppinglist.handlers;

import ru.eroonda.shoppinglist.dao.ShoppingListDao;

import javax.servlet.http.HttpServletRequest;

public class DeleteAllPurchasesHandler {
    public static void deleteAllSessionPurchases(ShoppingListDao dao, HttpServletRequest req) {

        dao.deleteAllSessionPurchases(req.getSession().getId());
        
    }
}
