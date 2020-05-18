<%--
  Created by IntelliJ IDEA.
  User: ale-k
  Date: 11.05.2020
  Time: 14:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%

%>
<html>
<head>
    <title>Главная</title>
</head>
<body>
<table border="1" cellpadding="5">
    <thead>
    <tr>
        <th>Id</th>
        <th>Логин</th>
        <th>Возраст</th>
        <th>Роль</th>
        <th>Редактирование</th>
        <th>Удаление</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${userData}" var="user">
        <tr>
            <td> ${user.id} </td>
            <td> ${user.username} </td>
            <td> ${user.age} </td>
            <td>
                <c:forEach items="${user.roles}" var="role">
                    ${role.role}<br/>
                </c:forEach>
            </td>
            <td>
                <form action="/admin/update" method="get">
                    <input type="hidden" name="id" value=${user.id}>
                    <button type="submit">Изменить</button>
                </form>
            </td>
            <td>
                <form action="/admin/delete" method="get">
                    <input type="hidden" name="id" value=${user.id}>
                    <button type="submit">Удалить</button>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<br/>
<form action="/admin/add">
    <button type="submit">Добавить пользователя</button>
</form>
<form action="/admin/drop" method="get">
    <button type="submit">Очистить таблицу</button>
</form>
<button onclick="location.href='/logout'">Выйти</button>
</body>
</html>
