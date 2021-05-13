package ru.eroonda.shoppinglist.handlers;

import ru.eroonda.shoppinglist.dao.ShoppingListDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddNewPurchaseHandler implements Handler {

    public void handle(ShoppingListDao dao, HttpServletRequest request, HttpServletResponse response) {

        dao.addNewPurchase(
                request.getParameter("purchase_name"),
                request.getParameter("count"),
                request.getParameter("price"),
                request.getSession().getId());
    }
}
