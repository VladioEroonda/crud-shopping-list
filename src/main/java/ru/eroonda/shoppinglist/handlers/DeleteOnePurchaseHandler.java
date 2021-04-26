package ru.eroonda.shoppinglist.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.eroonda.shoppinglist.dao.ShoppingListDao;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

public class DeleteOnePurchaseHandler {

    private static final Logger logger = LoggerFactory.getLogger(DeleteOnePurchaseHandler.class);

    public static void deleteOnePurchase(ShoppingListDao dao, HttpServletRequest req) {

        String[] purchaseData = req.getParameter("purchaseForDelete").split(";");
        logger.info("Current purchase data for delete : " +
                "\nS name: \"" + purchaseData[0] + "\"" +
                "\nI count: \"" + Integer.parseInt(purchaseData[1]) + "\"" +
                "\nD price: \"" + Double.parseDouble(purchaseData[2]) + "\"" +
                "\nSess ID:\"" + req.getSession().getId() + "\""
        );

        dao.deleteOneCurrentPurchase(
                purchaseData[0].trim(), //Почему-то без trim добавляет то ли пробелы, то ли переносы
                Integer.parseInt(purchaseData[1]),
                Double.parseDouble(purchaseData[2]),
                req.getSession().getId());

    }
}
