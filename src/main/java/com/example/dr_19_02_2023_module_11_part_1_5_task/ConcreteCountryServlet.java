package com.example.dr_19_02_2023_module_11_part_1_5_task;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name = "ConcreteCountryServlet", value = "/ConcreteCountryServlet")
public class ConcreteCountryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        ServletContext context=getServletContext();
//        //context.setAttribute("/AllBloknotConcreteCountry.jsp","/AllBloknotConcreteCountry.jsp");
//        context.getRequestDispatcher("/AllBloknotConcreteCountry.jsp").forward(request,response);
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
            //ServletContext context=getServletContext();
            //var name=context.getAttribute("countryName");
            String name=request.getParameter("countryName");
            String selectAll="SELECT *\n" +
                    "\tFROM bloknot where country like '"+name+"'";
                    // "\tFROM bloknot where country='Україна';";
            DataServlet.ShowData(out, statement, selectAll,false);
            statement.close();
            connection.close();
        } catch (SQLException e) {
            out.println(e.getMessage());
        }

    }
}
