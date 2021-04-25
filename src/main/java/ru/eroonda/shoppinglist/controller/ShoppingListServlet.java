package ru.eroonda.shoppinglist.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.eroonda.shoppinglist.dao.ShoppingListDao;
import ru.eroonda.shoppinglist.dao.ShoppingListDaoImpl;

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
        resp.getWriter().write("<html><h2><a href=\"index.jsp\">Try again!</a><h2><html>");
        resp.getWriter().flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ShoppingListDao dao = new ShoppingListDaoImpl();
        HttpSession session = req.getSession();
//        session.setMaxInactiveInterval(30);

        req.setCharacterEncoding("UTF-8");

        dao.addNewPurchase(
                req.getParameter("purchase_name"),
                req.getParameter("count"),
                req.getParameter("price"),
                session.getId());

        logger.info("POST. Adding new list position to DB");



//        resp.getWriter().write(session.getId());
//        resp.getWriter().flush();

        getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        logger.info("Starting session# " + se.getSession().getId());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        logger.info("Destroying session #" + se.getSession().getId());
    }
}
