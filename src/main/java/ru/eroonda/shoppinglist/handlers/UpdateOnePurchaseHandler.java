package ru.eroonda.shoppinglist.handlers;

import ru.eroonda.shoppinglist.dao.ShoppingListDao;

import javax.servlet.http.HttpServletRequest;

public class UpdateOnePurchaseHandler {

    public static void updateOnePurchase(ShoppingListDao dao, HttpServletRequest req) {

        //Поменять
        String[] purchaseData = req.getParameter("purchaseForDelete").split(";");

        dao.deleteOneCurrentPurchase(
                Integer.parseInt(purchaseData[0].trim()), // к 1му(0му) элементу добавляется лишний пробел хз почему, нужен .trim()
                purchaseData[1],
                Integer.parseInt(purchaseData[2]),
                Double.parseDouble(purchaseData[3]),
                req.getSession().getId());
    }
}
