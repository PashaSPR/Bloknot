package com.example.dr_19_02_2023_module_11_part_1_5_task;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name = "AllBloknotSoftServlet", value = "/AllBloknotSoftServlet")
public class AllBloknotSoftServlet extends HttpServlet {
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

            out.println("<h1>Блокноти з мякою обкладинкою</h1>");
            String selectAllSoft="SELECT id, namemanufacturer, namebloknot, countpages, covertype, country, appearance\n" +
                    "FROM public.bloknot\n" +
                    "where covertype='мяка';";
            DataServlet.ShowData(out, statement, selectAllSoft,false);
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
