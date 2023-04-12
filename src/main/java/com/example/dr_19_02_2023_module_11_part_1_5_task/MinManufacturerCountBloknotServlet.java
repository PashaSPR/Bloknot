package com.example.dr_19_02_2023_module_11_part_1_5_task;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name = "MinManufacturerCountBloknotServlet", value = "/MinManufacturerCountBloknotServlet")
public class MinManufacturerCountBloknotServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String url="jdbc:postgresql://localhost:5432/BloknotDB";
        String userName="postgres";
        String password="3538srp";
        PrintWriter out=response.getWriter();
        try {
            Class.forName("org.postgresql.Driver");

        }catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            Connection connection= DriverManager.getConnection(url,userName,password);
            Statement statement=connection.createStatement();

            out.println("<h1>Виробник з мінімальною кількістю блокнотів</h1>");
            String selectAll="SELECT namemanufacturer,count(Namebloknot) as s\n" +
                    "  FROM bloknot\n" +
                    "GROUP BY namemanufacturer\n" +
                    "order by s LIMIT 1;";
            ResultSet resultSet=statement.executeQuery(selectAll);
            out.println("<table border='1'>");//
            out.println("<tr><th>Назва виробника</th><th>Кількість блокнотів</th></tr>");
            while(resultSet.next()) {
                String resCountry = resultSet.getString(1);
                int CountNameBloknot=resultSet.getInt(2);
                out.println("<tr><td>" +resCountry + "</td><td>" +CountNameBloknot + "</td></tr>");//
            };
            out.println("</table><br>");
            out.println("<a href=\"index.jsp\">Повернутися</a><br>");
            statement.close();
            connection.close();
        } catch (SQLException e) {
            out.println(e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
