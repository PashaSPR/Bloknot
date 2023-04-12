package com.example.dr_19_02_2023_module_11_part_1_5_task;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name = "DataServlet", value = "/DataServlet")
public class DataServlet extends HttpServlet {
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

            String selectAll="SELECT id, namemanufacturer, namebloknot, countpages, covertype, country, appearance\n" +
                    "\tFROM bloknot;";
            //int rows=statement.executeUpdate(selectAll);
            ShowData(out, statement, selectAll,false);
//            out.println("<br>Оброблено "+resultSet.getRow()+" рядків<br>");
//            out.println("Оброблено "+resultSet.getFetchSize()+" рядків<br>");
            statement.close();
            connection.close();
        } catch (SQLException e) {
            out.println(e.getMessage());
        }
    }

    static void ShowData(PrintWriter out, Statement statement, String selectAll,boolean change) throws SQLException {
        ResultSet resultSet=statement.executeQuery(selectAll);

        out.println("<h1>УСІ ЗАПИСИ </h1>");
        out.println("<table border='1'>");//
        out.println("<tr><th>№ </th><th>Назва виробника</th><th>Назва блокноту</th><th>Кількість сторінок</th>" +
                "<th>Тип обкладинки</th><th>Країна виробник</th><th>Зовнішній вигляд</th><th>Редагування</th><th>Видалення</th></tr>");
        while(resultSet.next()){
            int id=resultSet.getInt(1);
            String resManufacturer=resultSet.getString(2);
            String resName=resultSet.getString(3);
            int resPages=resultSet.getInt(4);
            String resCoverType=resultSet.getString(5);
            String resCountry=resultSet.getString(6);
            String resAppearance=resultSet.getString(7);
            out.println("<tr><td>"+id + "</td><td>" +resManufacturer + "</td>"+
                    "<td>"+resName + "</td><td>" +resPages + "</td>"+
                    "<td>"+resCoverType + "</td><td>" +resCountry + "</td><td>"+resAppearance+"</td>" );
            if(change==true){ }
                out.println("<td>" +
                        "<form action=\"UpdateServlet\" method=\"get\">" +
                        "<input type=\"hidden\" name=\"id\" value=\"" + id + "\">" +
                        "<button type=\"submit\">Редагувати</button>" +
                        "</form>" +
                        "</td>" +
                        "<td>" +
                        "<form action=\"DeleteServlet\" method=\"post\">" +
                        "<input type=\"hidden\" name=\"id\" value=\"" + id + "\">" +
                        "<button type=\"submit\">Видалити</button>" +
                        "</form>" +
                        "</td>");
//                out.println("<td><button><a method=\"post\" href=\"updateBloknot.jsp?id="+id+"\">Редагувати</a></button></td>"+
//                        "<td><button><a method=\"post\" href=\"DeleteServlet?id="+id+"&name=Delete\">Видалити</a></button></td>");

//                out.println("<td><button><a method=\"post\" href=\"updateBloknot.jsp?id="+id+"\">Редагувати</a></button></td>"+
//                out.println("<td><button><a methods=\"post\" href=\"UpdateServlet?id="+id+"&name=Edit\">Редагувати</a></button></td>"+
//                        "<td><button><a method=\"post\" href=\"DeleteServlet?id="+id+"&name=Delete\"> Видалити</a></button></td>");

            out.println("</tr>");
            //out.println(resId+" "+resManufacturer+" "+resName+" "+resPages+" "+resCoverType+" "+resCountry+" "+resAppearance+"<br>");
            //out.println(String.format("%1-5d %2-15d %3-35d %4-5d %5-10d %6-10d %7-15d<br>",resId,resManufacturer,resName,resPages,resCoverType,resCountry,resAppearance));
        }
        out.println("</table><br>");
        out.println("<a href=\"index.jsp\">Повернутися</a><br>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
