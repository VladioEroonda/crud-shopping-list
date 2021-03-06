package ru.eroonda.shoppinglist.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.eroonda.shoppinglist.dao.ShoppingListDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

public class UpdateOnePurchaseHandler implements Handler {

    private final Logger logger = LoggerFactory.getLogger(UpdateOnePurchaseHandler.class);

    public void handle(ShoppingListDao dao, HttpServletRequest request, HttpServletResponse response) {

        String[] purchaseData = request.getParameter("purchaseForDeleteOrEdit").split(";");
        String newName = request.getParameter("new_purchase_name");
        String newCount = request.getParameter("new_count");
        String newPrice = request.getParameter("new_price");

        logger.info("DATA FOR UPDATE" + Arrays.toString(purchaseData) + newName + newName.length() + newCount + newPrice);

        dao.updateOneCurrentPurchase(
                Integer.parseInt(purchaseData[0].trim()), // к 1му(0му) элементу добавляется лишний пробел почему-то, нужен .trim()
                newName,
                Integer.parseInt(newCount),
                Double.parseDouble(newPrice),
                request.getSession().getId());
    }
}
