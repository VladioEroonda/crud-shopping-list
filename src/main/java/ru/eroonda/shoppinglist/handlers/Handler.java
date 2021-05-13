package ru.eroonda.shoppinglist.handlers;

import ru.eroonda.shoppinglist.dao.ShoppingListDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Handler {

    void handle(ShoppingListDao dao, HttpServletRequest request, HttpServletResponse response);

}
