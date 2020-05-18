<%--
  Created by IntelliJ IDEA.
  User: ale-k
  Date: 13.05.2020
  Time: 0:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Войти</title>
</head>
<body>
<h4>Пожалуйста, введите ваши логин и пароль</h4>
<form method="post">
    <label>Логин:
        <input name="j_username"><br/>
    </label>

    <label>Пароль:
        <input type="password" name="j_password"><br/>
    </label>

    <br/>

    <button type="submit">Войти</button>
</form>
<button onclick="location.href='/registration'">Регистрация</button>
</body>
</html>
