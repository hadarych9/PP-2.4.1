<%--
  Created by IntelliJ IDEA.
  User: ale-k
  Date: 16.05.2020
  Time: 20:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Регистрация пользователя</title>
</head>
<body>
<h4>Пожалуйста, введите следующие данные:</h4>
<form method="post">
    <label>Логин:
        <input type="text" name="name"><br/>
    </label>

    <label>Пароль:
        <input type="password" name="password"><br/>
    </label>

    <label>Возраст:
        <input type="number" name="age"><br/>
    </label>

    <button type="submit">Сохранить</button>
</form>
<button onclick="location.href='/login'">Вернуться</button>
</body>
</html>
