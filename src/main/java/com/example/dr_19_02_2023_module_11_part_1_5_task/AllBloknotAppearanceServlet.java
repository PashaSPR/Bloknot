package com.example.dr_19_02_2023_module_11_part_1_5_task;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name = "AllBloknotAppearanceServlet", value = "/AllBloknotAppearanceServlet")
public class AllBloknotAppearanceServlet extends HttpServlet {
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
            String optionAppearance="SELECT appearance\n" +
                    "\tFROM public.bloknot\n" +
                    "\tgroup by appearance";
            ResultSet resultSet=statement.executeQuery(optionAppearance);
            String selectValue="";
            while(resultSet.next()){
                String resOption=resultSet.getString(1);
                selectValue+=("<option value=\""+resOption + "\" >" +resOption + "</option>");
            }
            ServletContext servletContext = getServletContext();
            servletContext.setAttribute("selectValue",selectValue);
            servletContext.getRequestDispatcher("/AllBloknotAppearance.jsp").forward(request,response);
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
            Statement statement=connection.createStatement();
            String appearance=request.getParameter("selectAppearance");
            String filterAppearance="SELECT*\n" +
                    "\tFROM bloknot\n" +
                    "\twhere appearance like '"+appearance+"'";
            //out.println(appearance);
//            ServletContext servletContext = getServletContext();
//            servletContext.setAttribute("filterAppearance",filterAppearance);
//            servletContext.getRequestDispatcher("/AllBloknotAppearance.jsp").forward(request,response);
            DataServlet.ShowData(out, statement, filterAppearance,false);
            statement.close();
            connection.close();
        } catch (SQLException e) {
            out.println(e.getMessage());
        }

    }
}
