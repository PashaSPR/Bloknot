package com.example.dr_19_02_2023_module_11_part_1_5_task;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

/*Завдання 1:
        Створіть однотабличну базу даних «Блокноти». В таблиці зберігаємо інформацію про блокноти.
        Мінімальні вимоги:
         Назва фірми виробника блокнота
         Назва блокнота
         Кількість сторінок
         Тип обкладинки: тверда, м’яка
         Країна, виробник
         Зовнішній вигляд сторінки блокноту: клітинка, лінійка, порожньо
        Завдання 2:
        Створіть додаток для роботи з базою даних із першого завдання. Мінімальні вимоги:
         Приєднання до бази даних
         Від’єднання від бази даних
         Відображення всього вмісту таблиці з блокнотами
         Показати всі країни виробники
         Показати назви країн і кількість блокнотів із кожної країни
         Показати назву виробника і кількість блокнотів кожного виробника
        Завдання 3:
        Додати до другого завдання наступний функціонал
         Показати країну з найбільшою кількістю блокнотів
         Показати країну з найменшою кількістю блокнотів
         Показати виробника з найбільшою кількістю блокнотів
         Показати виробника з найменшою кількістю блокнотів
         Показати всі блокноти з твердою обкладинкою
         Показати всі блокноти з м’якою обкладинкою
        2
        Завдання 4:
        Додати до третього завдання наступний функціонал
         Показати всі блокноти конкретної країни
         Створити фільтр для відображення блокнотів по зовнішньому вигляді сторінки
         Створити фільтр по кількості сторінок
        Завдання 5:
        Додати до четвертого завдання наступний функціонал
         Додавання рядка
         Видалення рядка
         Оновлення рядка*/
@WebServlet(name = "bloknotServlet", value = "/bloknot-servlet")
public class BloknotServlet extends HttpServlet {
    String message="Connection OK!";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String url="jdbc:postgresql://localhost:5432/BloknotDB";
        String userName="postgres";
        String password="3538srp";
        PrintWriter out=resp.getWriter();
        out.println("<h1>" + message + "</h1>");
        try {
            Class.forName("org.postgresql.Driver");

        }catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            Connection connection= DriverManager.getConnection(url,userName,password);
            Statement statement=connection.createStatement();
            String createTable="create Table if not exists Bloknot(id INT Primary key GENERATED ALWAYS AS IDENTITY,nameManufacturer Varchar(30),NameBloknot Varchar(50)," +
                    "countPages int,CoverType Varchar(20),Country Varchar(30),Appearance Varchar(20))";
            int x=statement.executeUpdate(createTable);
            out.println("В таблиці створено "+x+" рядків<br>");
            out.println("<a href=\"index.jsp\">Повернутися</a><br>");
            statement.close();
            connection.close();
        } catch (SQLException e) {

            out.println(e.getMessage());
        }
    }
    public void destroy() {
    }
}
