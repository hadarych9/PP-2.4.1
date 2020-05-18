<%--
  Created by IntelliJ IDEA.
  User: ale-k
  Date: 11.05.2020
  Time: 16:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%
    request.setCharacterEncoding("UTF-8");
    boolean admin = (boolean) request.getAttribute("admin");
    String role;
    String changeRole;
    if(admin) {
        role = "user";
        changeRole = "Лишить полномочий администратора";
    }
    else {
        role = "admin";
        changeRole = "Сделать администратором";
    }
%>
<jsp:useBean id="user" scope="request" type="CRUD.model.User"/>
<html>
<head>
    <title>Добавление пользователя</title>
</head>
<body>
<form method="post">
    <input type="hidden" name="id" value="${user.id}">

    <label>Логин:
        <input type="text" name="name" value="${user.username}"><br/>
    </label>

    <label>Пароль:
        <input type="password" name="password"><br/>
    </label>

    <label>Возраст:
        <input type="number" name="age" value="${user.age}"><br/>
    </label>

    <input type="checkbox" name="role" value="<%= role%>"><%= changeRole%><br/>

    <button type="submit">Сохранить</button>
</form>
<button onclick="location.href='/admin/adminPage'">Вернуться на главную</button>
</body>
</html>
