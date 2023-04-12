package com.example.dr_19_02_2023_module_11_part_1_5_task;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name = "DeleteServlet", value = "/DeleteServlet")
public class DeleteServlet extends HttpServlet {
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
            String optionName="SELECT nameBloknot\n" +
                    "\tFROM public.bloknot\n";
            ResultSet resultSet=statement.executeQuery(optionName);
            String selectValue="";
            while(resultSet.next()){
                String resOption=resultSet.getString(1);
                selectValue+=("<option value=\""+resOption + "\" >" +resOption + "</option>");
            }
            ServletContext servletContext = getServletContext();
            servletContext.setAttribute("selectValue",selectValue);
            servletContext.getRequestDispatcher("/deleteBloknot.jsp").forward(request,response);
            statement.close();
            connection.close();
        } catch (SQLException e) {
            out.println(e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
            int id=Integer.parseInt(request.getParameter("id"));
            String deleteRowsID="DELETE FROM bloknot WHERE id = ?";
            PreparedStatement statement= connection.prepareStatement(deleteRowsID);
            statement.setInt(1,id);
            if(request.getParameter("deleteBloknot")!=null){
                String bloknot = request.getParameter("deleteBloknot");
                String deleteRows = "DELETE FROM bloknot WHERE nameBloknot = ?";
                PreparedStatement statementName = connection.prepareStatement(deleteRows);
                statementName.setString(1, bloknot);
            }
            int rows=statement.executeUpdate();
            out.println("В таблиці змінено "+rows+" рядків<br>");
            if (rows != 0) {
                String selectAll = "SELECT id, namemanufacturer, namebloknot, countpages, covertype, country, appearance\n" +
                        "\tFROM bloknot;";
                Statement statementShow = connection.createStatement();
                DataServlet.ShowData(out, statementShow, selectAll, true);
            }else {
            out.println("<a href=\"index.jsp\">Повернутися</a><br>");
            }
            statement.close();
            connection.close();
        } catch (SQLException e) {
            out.println(e.getMessage());
        }
    }
}
/*
            //String selectRows="select id FROM bloknot WHERE nameBloknot = ?";
            int id=Integer.parseInt(request.getParameter("id"));
            String deleteRowsID="DELETE FROM bloknot WHERE id = ?";
            PreparedStatement statement= connection.prepareStatement(deleteRowsID);
            statement.setInt(1,id);
 */