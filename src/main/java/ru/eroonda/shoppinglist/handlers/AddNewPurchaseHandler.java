package ru.eroonda.shoppinglist.handlers;

import ru.eroonda.shoppinglist.dao.ShoppingListDao;

import javax.servlet.http.HttpServletRequest;

public class AddNewPurchaseHandler {
    public void addNewPurchase(ShoppingListDao dao, HttpServletRequest req) {

        dao.addNewPurchase(
                req.getParameter("purchase_name"),
                req.getParameter("count"),
                req.getParameter("price"),
                req.getSession().getId());
    }
}
