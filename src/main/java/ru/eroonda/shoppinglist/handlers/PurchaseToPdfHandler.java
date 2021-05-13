package ru.eroonda.shoppinglist.handlers;

import ru.eroonda.shoppinglist.builder.PdfBuilder;
import ru.eroonda.shoppinglist.dao.ShoppingListDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PurchaseToPdfHandler implements Handler {

    @Override
    public void handle(ShoppingListDao dao, HttpServletRequest request, HttpServletResponse response) {

        PdfBuilder.build(dao, request, response);

    }
}
