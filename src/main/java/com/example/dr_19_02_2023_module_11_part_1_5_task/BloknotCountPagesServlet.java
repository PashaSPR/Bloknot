package com.example.dr_19_02_2023_module_11_part_1_5_task;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name = "BloknotCountPagesServlet", value = "/BloknotCountPagesServlet")
public class BloknotCountPagesServlet extends HttpServlet {
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
            String optionCountPages="SELECT countPages\n" +
                    "\tFROM public.bloknot\n" +
                    "\tgroup by countPages";
            ResultSet resultSet=statement.executeQuery(optionCountPages);
            String selectValue="";
            while(resultSet.next()){
                String resOption=resultSet.getString(1);
                selectValue+=("<option value=\""+resOption + "\" >" +resOption + "</option>");
            }
            ServletContext servletContext = getServletContext();
            servletContext.setAttribute("selectValue",selectValue);
            servletContext.getRequestDispatcher("/AllBloknotCountPages.jsp").forward(request,response);
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
            String countPages=request.getParameter("selectCountPages");
            String filterCountPages="SELECT * \n" +
                    "\tFROM bloknot \n" +
                    "\twhere countPages ='"+countPages+"'";
            DataServlet.ShowData(out, statement, filterCountPages,false);
            statement.close();
            connection.close();
        } catch (SQLException e) {
            out.println(e.getMessage());
        }
    }
}
