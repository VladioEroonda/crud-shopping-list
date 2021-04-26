package ru.eroonda.shoppinglist.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.eroonda.shoppinglist.dao.ShoppingListDao;
import ru.eroonda.shoppinglist.dao.ShoppingListDaoImpl;
import ru.eroonda.shoppinglist.handlers.AddNewPurchaseHandler;
import ru.eroonda.shoppinglist.handlers.DeleteAllPurchasesHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebListener;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebListener
@WebServlet(name = "listServlet", value = "/general-servlet")
public class ShoppingListServlet extends HttpServlet implements HttpSessionListener {

    private final Logger logger = LoggerFactory.getLogger(ShoppingListServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ShoppingListDao dao = new ShoppingListDaoImpl();

//        HttpSession session = req.getSession();
//        session.setMaxInactiveInterval(30);

        req.setCharacterEncoding("UTF-8");

        if(req.getParameter("deleteOneLine") != null){

            logger.info("Delete line button was pushed");
        }
        if(req.getParameter("changeOneLine") != null){

            logger.info("Change line button was pushed");
        }
        if(req.getParameter("deleteAllLines") != null){
            new DeleteAllPurchasesHandler().deleteAllSessionPurchases(dao,req);
            logger.info("Deleted all puchases from current session" + req.getSession().getId());
        }
        if(req.getParameter("addOneLine") != null){
            new AddNewPurchaseHandler().addNewPurchase(dao, req);
            logger.info("Added new list position to DB, session id #" + req.getSession().getId());
        }

        getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        logger.info("Starting session #" + se.getSession().getId());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        logger.info("Destroying session #" + se.getSession().getId());
        ShoppingListDao dao = new ShoppingListDaoImpl();
        dao.deleteAllSessionPurchases(se.getSession().getId());
        logger.info("All purchases from session #" + se.getSession().getId() + " was deleted from BD");
    }
}
