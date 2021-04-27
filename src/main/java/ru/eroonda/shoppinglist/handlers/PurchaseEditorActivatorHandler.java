package ru.eroonda.shoppinglist.handlers;

import ru.eroonda.shoppinglist.dao.ShoppingListDao;

import javax.servlet.http.HttpServletRequest;

public class PurchaseEditorActivatorHandler {
    public static void setIsChangingAsTrue(ShoppingListDao dao, HttpServletRequest req){

        String[] purchaseData = req.getParameter("purchaseForDeleteOrEdit").split(";");

        dao.setIsChangingAsTrueForOnePurchase(
                Integer.parseInt(purchaseData[0].trim())); // из стринга вытаскиваем только уникальный айди в базе
    }
}
