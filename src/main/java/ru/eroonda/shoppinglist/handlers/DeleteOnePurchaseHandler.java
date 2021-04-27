package ru.eroonda.shoppinglist.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.eroonda.shoppinglist.controller.ShoppingListServlet;
import ru.eroonda.shoppinglist.dao.ShoppingListDao;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

public class DeleteOnePurchaseHandler {

    private static final Logger logger = LoggerFactory.getLogger(DeleteOnePurchaseHandler.class);

    public static void deleteOnePurchase(ShoppingListDao dao, HttpServletRequest req) {

        String[] purchaseData = req.getParameter("purchaseForDeleteOrEdit").split(";");

        //TODO убери логгер
        logger.info(Arrays.toString(purchaseData));

        dao.deleteOneCurrentPurchase(
                Integer.parseInt(purchaseData[0].trim()), // к 1му(0му) элементу добавляется лишний пробел хз почему, нужен .trim()
                purchaseData[1],
                Integer.parseInt(purchaseData[2]),
                Double.parseDouble(purchaseData[3]),
                req.getSession().getId());
    }
}
