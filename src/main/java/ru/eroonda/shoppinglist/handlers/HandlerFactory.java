package ru.eroonda.shoppinglist.handlers;

import ru.eroonda.shoppinglist.handlers.Handler;

public class HandlerFactory {

    public static Handler getActionInstance(String actionDescription) {

        Handler handler = null;

        switch (actionDescription) {
            case "deleteOneLine":
                handler = new DeleteOnePurchaseHandler();
                break;
            case "editOneLine":
                handler = new PurchaseEditorActivatorHandler();
                break;
            case "updateOneLine":
                handler = new UpdateOnePurchaseHandler();
                break;
            case "deleteAllLines":
                handler = new DeleteAllPurchasesHandler();
                break;
            case "addOneLine":
                handler = new AddNewPurchaseHandler();
                break;
            case "saveAsXlsx":
                handler = new PurchaseToXlsxHandler();
                break;
            case "saveAsPdf":
                handler = new PurchaseToPdfHandler();
                break;
            default:
                throw new IllegalArgumentException("Wrong action type");
        }

        return handler;
    }
}
