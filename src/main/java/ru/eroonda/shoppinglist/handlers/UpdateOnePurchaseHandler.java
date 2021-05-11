package ru.eroonda.shoppinglist.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.eroonda.shoppinglist.dao.ShoppingListDao;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 *
 */

public class UpdateOnePurchaseHandler {

    private static final Logger logger = LoggerFactory.getLogger(UpdateOnePurchaseHandler.class);

    public static void updateOnePurchase(ShoppingListDao dao, HttpServletRequest req) {

        String[] purchaseData = req.getParameter("purchaseForDeleteOrEdit").split(";");
        String newName = req.getParameter("new_purchase_name");
        String newCount = req.getParameter("new_count");
        String newPrice = req.getParameter("new_price");

        logger.info("DATA FOR UPDATE" + Arrays.toString(purchaseData) + newName + newName.length() + newCount + newPrice);

        dao.updateOneCurrentPurchase(
                Integer.parseInt(purchaseData[0].trim()), // к 1му(0му) элементу добавляется лишний пробел почему-то, нужен .trim()
                newName,
                Integer.parseInt(newCount),
                Double.parseDouble(newPrice),
                req.getSession().getId());
    }
}
