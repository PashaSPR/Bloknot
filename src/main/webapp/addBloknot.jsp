<%--
  Created by IntelliJ IDEA.
  User: Паша
  Date: 28.03.2023
  Time: 22:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Додавання блокноту</title>
<%--  <link href="style.css" rel="stylesheet">--%>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
</head>
<body>
<form class="row gy-2 gx-3 align-items-center" action="AddServlet" method="post">
  <div class="flex-box">
    <label class="block" for="Manufacturer">Назва виробника</label>
    <input type="text" name="Manufacturer"  class="form-control" id="Manufacturer" placeholder="Manufacturer"><br>
    <label class="block" for="NameBloknot">Назва блокноту</label>
    <input type="text" name="NameBloknot" class="form-control" id="NameBloknot" placeholder="NameBloknot"><br>
    <label class="block" for="countPages">Кількість сторінок</label>
    <input type="number" name="countPages" class="form-control" id="countPages" placeholder="countPages"><br>
    <label class="block" for="coverType">Тип обкладинки</label>
    <input type="text" name="coverType" class="form-control" id="coverType" placeholder="coverType"><br>
    <label class="block" for="country">Назва країни</label>
    <input type="text" name="country" class="form-control" id="country" placeholder="country"><br>
    <label class="block" for="Appearance">Зовнішній вигляд</label>
    <input type="text" name="Appearance" class="form-control" id="Appearance" placeholder="Appearance"><br>
  </div>
  <div class="col-auto">
  <button type="submit" class="btn btn-primary highlighted">Submit</button>
    </div>
</form>
</body>
</html>
