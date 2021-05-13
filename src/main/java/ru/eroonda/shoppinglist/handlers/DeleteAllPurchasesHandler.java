package ru.eroonda.shoppinglist.handlers;

import ru.eroonda.shoppinglist.dao.ShoppingListDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteAllPurchasesHandler implements Handler {

    public void handle(ShoppingListDao dao, HttpServletRequest request, HttpServletResponse response) {

        dao.deleteAllSessionPurchases(request.getSession().getId());

    }
}
