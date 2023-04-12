
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>тут</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
</head>
<body>
<form class="row gy-2 gx-3 align-items-center" action="ConcreteCountryServlet" method="post">
  <div class="col-auto">
    <label class="visually-hidden" for="autoSizingInput">Назва країни</label>
    <input type="text" name="countryName" class="form-control" id="autoSizingInput" placeholder="Country">
  </div>
  <div class="block">
    <button type="submit" class="btn btn-primary col-first">Submit</button>
  </div>
</form>
<%--<div id="result-container">--%>
<%--  ${bloknotData}--%>
<%--</div>--%>
</body>
</html>