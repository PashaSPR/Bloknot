<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Фільтр по кількості сторінок</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
</head>
<body>
<form class="row gy-2 gx-3 align-items-center" action="BloknotCountPagesServlet" method="post">
    <div class="col-auto">
        <p>Виберіть кількість сторінок:</p>
        <select name="selectCountPages">
            ${selectValue}
        </select>
    </div>
    <div class="block">
        <button type="submit" class="btn btn-primary col-first">Submit</button>
    </div>
</form>
</body>
</html>