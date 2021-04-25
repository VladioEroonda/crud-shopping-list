<%@ page import="ru.eroonda.shoppinglist.dao.ShoppingListDao" %>
<%@ page import="ru.eroonda.shoppinglist.dao.ShoppingListDaoImpl" %>
<%@ page import="ru.eroonda.shoppinglist.entity.ShoppingList" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    Class.forName("org.postgresql.Driver");
    ShoppingListDao dao = new ShoppingListDaoImpl();
    double total_cost = 0;
    int purchase_count=1;
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>Список затрат</title>
</head>
<body>
<table border="1">
    <caption>Ваш список покупок: </caption>
    <tr>
        <th>№</th>
        <th>Наименование товара</th>
        <th>Кол-во</th>
        <th>Цена за 1 шт</th>
        <th>Итого:</th>
    </tr>
    <%
        List<ShoppingList> allPurchases = dao.getAllPurchases();
        for (ShoppingList purchase : allPurchases) {
    %>
    <tr>
        <th><%= purchase_count %>
        </th>
        <th align="left"><%=purchase.getName() %>
        </th>
        <th><%=purchase.getCount() %>
        </th>
        <th><%=  purchase.getPrice() %>
        </th>
        <th><%= (purchase.getCount() * purchase.getPrice()) %>
        </th>
    </tr>

    <%
            total_cost += purchase.getPrice()*purchase.getCount();
            purchase_count++;
        }
    %>
    <tr>
        <th colspan="5">Всего позиций: <%=allPurchases.isEmpty() ? 0 : allPurchases.size() %>
        </th>
    <tr>
    <tr>
        <th colspan="5">Общая сумма: <%=total_cost%></th>
    <tr>


        <%--    <tr>--%>
        <%--        <th>№</th>--%>
        <%--        <th>Наименование товара</th>--%>
        <%--        <th>Кол-во</th>--%>
        <%--        <th>Цена за 1 шт</th>--%>
        <%--        <th>Итого:</th>--%>
        <%--    </tr>--%>
        <%--        <th>--%>
        <%--            <form action="/list-servlet" method="PUT">--%>
        <%--                <input type="submit" value="Изменить">--%>
        <%--            </form>--%>
        <%--        </th>--%>
        <%--        <th>--%>
        <%--            <form action="/list-servlet" method="DELETE">--%>
        <%--                <input type="submit" value="Удалить">--%>
        <%--            </form>--%>
        <%--        </th>--%>
        <%--    </tr>--%>

</table>

<br>
<br>
<br>
<h3>Добавьте свой товар: </h3>
<form action="general-servlet" method="POST">
    Товар:
    <input type="text" name="purchase_name" value="">
    Количество:
    <input type="text" name="count" value="">
    Цена:
    <input type="text" name="price" value="">
    <br><br>
    <input type="submit" value="Добавить в список">
</form>
<br/>
</body>
</html>