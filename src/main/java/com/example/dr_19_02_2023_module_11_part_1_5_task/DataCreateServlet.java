package com.example.dr_19_02_2023_module_11_part_1_5_task;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet(name = "Data-Create-Servlet", value = "/DataCreateServlet")
public class DataCreateServlet extends HttpServlet {
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
            //-------------------------------------------------------------------
            String addRows="INSERT INTO Bloknot (nameManufacturer,NameBloknot," +
                    "countPages,CoverType,Country,Appearance) " +
                    "VALUES('BuroMax','Блокнот на пружині зверху, SHOTLANDKA',48,'мяка','Україна','клітинка'),"+
                    "('Optima','Діловий нотатник Optima Sea А5',128,'тверда','Німеччина','лінія'),"+
                    "('BuroMax','Блокнот UKRAINE, А5 синій',96,'тверда','Україна','клітинка'),"+
                    "('BuroMax','Блокнот Herlitz College Block A4',100,'мяка','Німеччина','клітинка'),"+
                    "('Axent','Записна книжка Axent Partner 95х140мм',96,'тверда','Китай','клітинка')";
            Statement statementAddRows=connection.createStatement();
            int rows=statementAddRows.executeUpdate(addRows);
            out.println("В таблицю записано "+rows+" рядків<br>");
            out.println("<a href=\"index.jsp\">Повернутися</a><br>");
            statementAddRows.close();
            connection.close();
        } catch (SQLException e) {

            out.println(e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        ServletContext context=getServletContext();
//        context.setAttribute("/index.jsp","/index.jsp");
//        context.getRequestDispatcher("/index.jsp").forward(request,response);
    }
}
