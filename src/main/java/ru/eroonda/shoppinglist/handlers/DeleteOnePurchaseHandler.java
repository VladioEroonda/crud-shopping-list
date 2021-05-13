package ru.eroonda.shoppinglist.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.eroonda.shoppinglist.dao.ShoppingListDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

public class DeleteOnePurchaseHandler implements Handler {

    private final Logger logger = LoggerFactory.getLogger(DeleteOnePurchaseHandler.class);

    public void handle(ShoppingListDao dao, HttpServletRequest request, HttpServletResponse response) {

        String[] purchaseData = request.getParameter("purchaseForDeleteOrEdit").split(";");

        logger.info(Arrays.toString(purchaseData));

        dao.deleteOneCurrentPurchase(
                Integer.parseInt(purchaseData[0].trim()), // к 1му(0му) элементу добавляется лишний пробел почему-то, нужен .trim()
                purchaseData[1],
                Integer.parseInt(purchaseData[2]),
                Double.parseDouble(purchaseData[3]),
                request.getSession().getId());
    }
}
