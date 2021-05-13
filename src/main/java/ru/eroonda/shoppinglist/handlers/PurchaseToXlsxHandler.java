package ru.eroonda.shoppinglist.handlers;

import ru.eroonda.shoppinglist.builder.XlsxBuilder;
import ru.eroonda.shoppinglist.dao.ShoppingListDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PurchaseToXlsxHandler implements Handler {

    @Override
    public void handle(ShoppingListDao dao, HttpServletRequest request, HttpServletResponse response) {

        XlsxBuilder.build(dao, request, response);

    }

}
