<%@ page import="java.util.*" %>
<%@ page import="CRUD.model.Role" %><%--
  Created by IntelliJ IDEA.
  User: ale-k
  Date: 11.05.2020
  Time: 16:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<jsp:useBean id="user" scope="request" type="CRUD.model.User"/>
<%
    request.setCharacterEncoding("UTF-8");
    String userChecked = "";
    String adminChecked = "";

    for (Role role : user.getRoles()) {
        if (role.getRole().equals("ROLE_user")) {
            userChecked = "checked";
        } else if(role.getRole().equals("ROLE_admin")) {
            adminChecked = "checked";
        }
    }
%>
<html>
<head>
    <title>Редактирование пользователя</title>
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

    <input type="checkbox" name="roles" value="user" <%= userChecked%>>Пользователь<br/>

    <input type="checkbox" name="roles" value="admin" <%= adminChecked%>>Администратор<br/>

    <button type="submit">Сохранить</button>
</form>
<button onclick="location.href='/admin/adminPage'">Вернуться на главную</button>
</body>
</html>
