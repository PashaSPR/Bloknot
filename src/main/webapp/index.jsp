<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Програма роботи з блокнотами</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
<%--    <link href="style.css" rel="stylesheet">--%>
</head>
<body>
<h1 class="center"><%= "Виберіть дію!" %></h1>
<div class="container">
    <div class="btn-group-vertical" role="group" aria-label="Vertical button group">
    <button ><a href="bloknot-servlet">Приєднання бази даних,створення таблиці</a></button>
    <button ><a href="DataCreateServlet">Наповнення данними</a></button>
    <button ><a href="DataServlet">Відображення всього вмісту таблиці з блокнотами</a></button>
    <button ><a href="AllCountryServlet">Показати всі країни виробники</a></button>
    <button ><a href="CountryCountBloknotServlet">Показати назви країн і кількість блокнотів із кожної країни</a></button>
    <button ><a href="ManufacturerCountBloknotServlet">Показати назву виробника і кількість блокнотів кожного виробника</a></button>
    <button ><a href="MaxCountryCountBloknotServlet">Показати країну з найбільшою кількістю блокнотів</a></button>
    <button ><a href="MinCountryCountBloknotServlet">Показати країну з найменшою кількістю блокнотів</a> </button>
    <button ><a href="MaxManufacturerCountBloknotServlet">Показати виробника з найбільшою кількістю блокнотів</a></button>
    <button ><a href="MinManufacturerCountBloknotServlet">Показати виробника з найменшою кількістю блокнотів</a></button>
    <button ><a href="AllBloknotHardServlet">Показати всі блокноти з твердою обкладинкою</a></button>
    <button ><a href="AllBloknotSoftServlet">Показати всі блокноти з мякою обкладинкою</a></button>
    <button ><a href="AllBloknotConcreteCountry.jsp">Показати всі блокноти конкретної країни</a></button>
    <button ><a href="AllBloknotAppearanceServlet">Створити фільтр для відображення блокнотів по зовнішньому вигляді сторінки</a></button>
    <button ><a href="BloknotCountPagesServlet">Створити фільтр по кількості сторінок</a></button>
    <button ><a href="addBloknot.jsp">Додавання рядка</a></button>
    <button ><a href="DataServlet">Змінити дані</a></button>
<%--    <button ><a href="deleteBloknot.jsp">Видалення рядка по назві блокноту</a></button>--%>
<%--    <button ><a href="updateBloknot.jsp">Оновлення рядка</a></button> --%>
        </div>
</div>


</body>
</html>
<%--public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("User_name");
        String password = request.getParameter("password");
        String gender = request.getParameter("gender");
        String[] skills = request.getParameterValues("skills");
        ServletContext servletContext = getServletContext();
        servletContext.setAttribute("User_name",name);
        servletContext.setAttribute("password", password);
        servletContext.setAttribute("gender", gender);
        String strSkills="";
        for (String skill: skills ){
            strSkills+=" "+skill;
        }
        servletContext.setAttribute("skills",strSkills);
        servletContext.getRequestDispatcher("/showPage.jsp").forward(request,response);
        AllBloknotAppearance.jsp
    }--%>
