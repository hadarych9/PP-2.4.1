<%--
  Created by IntelliJ IDEA.
  User: ale-k
  Date: 13.05.2020
  Time: 0:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="user" scope="request" type="CRUD.model.User"/>
<html>
<head>
    <title>Страница пользователя ${user.username}</title>
</head>
<body>
<table border="1" cellpadding="5">
    <thead>
    <tr>
        <th>id</th>
        <th>Логин</th>
        <th>Возраст</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td>${user.id}</td>
        <td>${user.username}</td>
        <td>${user.age}</td>
    </tr>
    </tbody>
</table>
<br/>
<button onclick="location.href='/logout'">Выйти</button>
</body>
</html>
