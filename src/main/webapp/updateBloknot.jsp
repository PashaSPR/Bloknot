<%--
  Created by IntelliJ IDEA.
  User: Паша
  Date: 29.03.2023
  Time: 0:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Редагування блокноту</title>
<%--    <link href="style.css" rel="stylesheet">--%>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">

</head>
<body>
Select для вибору назви блокноту
Форма приймає дані від Select
Форма редагування надсилає дані у сервлет(це ми вже вміємо)

<form class="row gy-2 gx-3 align-items-center" action="UpdateServlet" method="post">
    <div class="col-auto">
        <input type="hidden" name="id" id="id" value="${id}"><br>
        <label class="bold" for="Manufacturer" >Назва виробника</label>
        <input type="text" name="Manufacturer" class="form-control" value="${Manufacturer}" id="Manufacturer" placeholder="Manufacturer"><br>
        <label class="bold" for="NameBloknot" >Назва блокноту</label>
        <input type="text" name="NameBloknot" class="form-control" id="NameBloknot" value="${NameBloknot}" placeholder="NameBloknot"><br>
        <label class="bold" for="countPages" >Кількість сторінок</label>
        <input type="number" name="countPages" class="form-control" id="countPages" value="${countPages}" placeholder="countPages"><br>
        <label class="bold" for="coverType">Тип обкладинки</label>
        <input type="text" name="coverType" class="form-control" id="coverType" value="${coverType}" placeholder="coverType"><br>
        <label class="bold" for="country">Назва країни</label>
        <input type="text" name="country" class="form-control" id="country" value="${country}" placeholder="country"><br>
        <label class="bold" for="Appearance">Зовнішній вигляд</label>
        <input type="text" name="Appearance" class="form-control" value="${Appearance}" id="Appearance" placeholder="Appearance"><br>
    </div>
    <div class="block">
        <button type="submit" class="btn btn-primary col-first">Submit</button>
    </div>
</form>
</body>
</html>
