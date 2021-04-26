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

<a href="index.jsp" style="color: white">Главная страница</a>
<form action="general-servlet" method="POST" >
    <input type="submit" value="Очистить весь список" class="border-button2" name="deleteAllLines" style="float: right;">
</form>
<br>
<br>
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
            <form action="general-servlet" method="POST">
                <input type="submit" value="Изменить" class="border-button2" name="changeOneLine">
                <input type="submit" value="Удалить" class="border-button2" name="deleteOneLine">
                <input type="hidden" name="purchaseForDelete" value="
<%= purchase.getId() + ";"  + purchase.getName() + ";" + purchase.getCount() + ";" + purchase.getPrice() %> ">
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
    <input type="text" name="purchase_name" class="input-field" required>
    Количество:
    <input type="number" name="count" class="input-field" required min="1">
    Цена:
    <input type="number" name="price" class="input-field" required min="0" step="0.1">
    <br><br>
    <input type="submit" value="Добавить в список" class="border-button" name="addOneLine">
</form>
<br/>

</body>
</html>