package com.example.dr_19_02_2023_module_11_part_1_5_task;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name = "CountryCountBloknotServlet", value = "/CountryCountBloknotServlet")
public class CountryCountBloknotServlet extends HttpServlet {
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

            out.println("<h1>Країни</h1>");
            String selectAll="SELECT  country, Count(Namebloknot)\n" +
                    "FROM public.bloknot\n" +
                    "group by country;";
            ResultSet resultSet=statement.executeQuery(selectAll);
            out.println("<table border='1'>");//
            out.println("<tr><th>№ </th><th>Країна-виробник</th><th>Кількість блокнотів</th></tr>");
                int resId = 1;
            while(resultSet.next()) {
                String resCountry = resultSet.getString(1);
                int CountNameBloknot=resultSet.getInt(2);
                out.println("<tr><td>" +resId + "</td><td>" +resCountry + "</td><td>" +CountNameBloknot + "</td></tr>");
                resId++;

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
