package com.example.dr_19_02_2023_module_11_part_1_5_task;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name = "AddServlet", value = "/AddServlet")
public class AddServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
//            Statement statement=connection.createStatement();
            String Manufacturer=request.getParameter("Manufacturer");
            String nameBloknot=request.getParameter("NameBloknot");
            Integer count=Integer.parseInt(request.getParameter("countPages"));
            String coverType=request.getParameter("coverType");
            String country=request.getParameter("country");
            String appearance=request.getParameter("Appearance");
            String addBloknot="INSERT INTO Bloknot (nameManufacturer,NameBloknot," +
                    "countPages,CoverType,Country,Appearance) " +
                    "VALUES (?,?,?,?,?,?)";
                    //"VALUES ('"+Manufacturer+"','"+nameBloknot+"',"+count+",'"+coverType+"','"+country+"','"+appearance+"')";
            PreparedStatement statement=connection.prepareStatement(addBloknot);
            statement.setString(1,Manufacturer);
            statement.setString(2,nameBloknot);
            statement.setInt(3,count);
            statement.setString(4,coverType);
            statement.setString(5,country);
            statement.setString(6,appearance);
            int rows=statement.executeUpdate();
            //Для виведення
            //---------------------------------------------------
        Statement statementShow=connection.createStatement();
            out.println("В таблицю записано рядків: "+rows+" <br>");
            String selectAll="SELECT id, namemanufacturer, namebloknot, countpages, covertype, country, appearance\n" +
                    "\tFROM bloknot;";
    DataServlet.ShowData(out, statementShow, selectAll,true);
            //--------------------------------------------------
            out.println("<a href=\"index.jsp\">Повернутися</a><br>");
            statement.close();
            connection.close();
        } catch (SQLException e) {
            out.println(e.getMessage());
        }
    }
}
