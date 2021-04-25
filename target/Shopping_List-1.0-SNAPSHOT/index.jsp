<%@ page import="ru.eroonda.shoppinglist.dao.ShoppingListDao" %>
<%@ page import="ru.eroonda.shoppinglist.dao.ShoppingListDaoImpl" %>
<%@ page import="ru.eroonda.shoppinglist.entity.ShoppingList" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    Class.forName("org.postgresql.Driver");
    ShoppingListDao dao = new ShoppingListDaoImpl();
    double total_cost = 0;
    int purchase_count = 1;
%>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="style.css">
    <meta charset="utf-8"/>
    <title>Список покупок</title>
</head>
<body>

<table class="table_dark" >
    <h3 align="left">Ваш список покупок:</h3>
    <tr>
        <th>№</th>
        <th>Наименование товара</th>
        <th>Кол-во</th>
        <th>Цена за 1 шт</th>
        <th>Итого:</th>
        <th bgcolor="#333336" class="th2"></th>
        <th bgcolor="#333336" class="th2"></th>

    </tr>
    <%
        List<ShoppingList> allPurchases = dao.getAllPurchases(request.getRequestedSessionId());
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

        <th bgcolor="#333336">
            <form action="/list-servlet" method="PUT">
                <input type="submit" value="Изменить" class="border-button2">
            </form>
            <form action="/list-servlet" method="DELETE">
                <input type="submit" value="Удалить" class="border-button2">
            </form>
        </th>
        <th bgcolor="#333336">

        </th>

    </tr>

    <%
            total_cost += purchase.getPrice() * purchase.getCount();
            purchase_count++;
        }
    %>
    <tr>
    <th colspan="5">Всего позиций: <%=allPurchases.isEmpty() ? 0 : allPurchases.size() %></th>
        <th bgcolor="#333336" class="th2"></th>
        <th bgcolor="#333336" class="th2"></th>
    </tr>
    <tr>
    <th colspan="5">Общая сумма: <%=total_cost%></th>
    <th bgcolor="#333336" class="th2"></th>
    <th bgcolor="#333336" class="th2"></th>
    <tr>


</table>

<br>
<br>
<br>
<h3>Добавьте свой товар: </h3>
<form action="general-servlet" method="POST">
    Товар:
    <input type="text" name="purchase_name" class="input-field">
    Количество:
    <input type="text" name="count" class="input-field">
    Цена:
    <input type="text" name="price" class="input-field">
    <br><br>
    <input type="submit" value="Добавить в список" class="border-button">
</form>
<br/>
</body>
</html>