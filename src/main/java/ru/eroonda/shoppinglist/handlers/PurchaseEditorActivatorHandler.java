package ru.eroonda.shoppinglist.handlers;

import ru.eroonda.shoppinglist.dao.ShoppingListDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PurchaseEditorActivatorHandler implements Handler {

    public void handle(ShoppingListDao dao, HttpServletRequest request, HttpServletResponse response) {

        String[] purchaseData = request.getParameter("purchaseForDeleteOrEdit").split(";");

        dao.setIsChangingAsTrueForOnePurchase(
                Integer.parseInt(purchaseData[0].trim())); // из стринга вытаскиваем только уникальный айди в базе
    }
}
