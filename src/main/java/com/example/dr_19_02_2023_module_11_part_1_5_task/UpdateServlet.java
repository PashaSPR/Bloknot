package com.example.dr_19_02_2023_module_11_part_1_5_task;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import static com.example.dr_19_02_2023_module_11_part_1_5_task.DataServlet.ShowData;

@WebServlet(name = "UpdateServlet", value = "/UpdateServlet")
public class UpdateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String url="jdbc:postgresql://localhost:5432/BloknotDB";
        String userName="postgres";
        String password="3538srp";
        PrintWriter out=response.getWriter();
        ResultSet resultSet = null;
            int id = Integer.parseInt(request.getParameter("id"));
        try {
            Class.forName("org.postgresql.Driver");

        }catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            Connection connection= DriverManager.getConnection(url,userName,password);
            PreparedStatement statement=null;
            String selectBloknotById = "SELECT * FROM Bloknot WHERE id=?";
            statement = connection.prepareStatement(selectBloknotById);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

           // resultSet = statement.executeQuery("SELECT * FROM bloknot WHERE id = " + id);

            // виведення форми для редагування запису
            if (resultSet.next()) {
                String Manufacturer = resultSet.getString("nameManufacturer");
                String NameBloknot = resultSet.getString("NameBloknot");
                int countPages = resultSet.getInt("countPages");
                String coverType = resultSet.getString("CoverType");
                String country = resultSet.getString("Country");
                String Appearance = resultSet.getString("Appearance");
                out.println("<html>");
                out.println("<html><head>\n" +
                        "    <title>Редагувати</title>\n" +
                        "    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css\" rel=\"stylesheet\"\n" +
                        "          integrity=\"sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD\" crossorigin=\"anonymous\">\n" +
                        "\n" +
                        "\n" +
                        "</head>");//Не відображає Submit
                out.println("<body>");
                out.println("<h1>Редагування блокнота</h1>");
                out.println("<form class=\"row gy-2 gx-3 align-items-center\" action=\"UpdateServlet\" method=\"post\">");
                out.println("<div class=\"col-auto\">");
                out.println("<input type=\"hidden\" name=\"id\" id=\"id\" value=\"" + id + "\"><br>");
                out.println("<label class=\"input-group-text\" for=\"Manufacturer\" >Назва виробника</label>");
                out.println("<input type=\"text\" name=\"Manufacturer\" class=\"form-control\" value=\"" + Manufacturer + "\" id=\"Manufacturer\" placeholder=\"Manufacturer\"><br>");
                out.println("<label class=\"input-group-text\" for=\"NameBloknot\" >Назва блокноту</label>");
                out.println("<input type=\"text\" name=\"NameBloknot\" class=\"form-control\" id=\"NameBloknot\" value=\"" + NameBloknot + "\" placeholder=\"NameBloknot\"><br>");
                out.println("<label class=\"input-group-text\" for=\"countPages\" >Кількість сторінок</label>");
                out.println("<input type=\"number\" name=\"countPages\" class=\"form-control\" id=\"countPages\" value=\"" + countPages + "\" placeholder=\"countPages\"><br>");
                out.println("<label class=\"input-group-text\" for=\"coverType\">Тип обкладинки</label>");
                out.println("<input type=\"text\" name=\"coverType\" class=\"form-control\" id=\"coverType\" value=\"" + coverType + "\" placeholder=\"coverType\"><br>");
                out.println("<label class=\"input-group-text\" for=\"country\">Назва країни</label>");
                out.println("<input type=\"text\" name=\"country\" class=\"form-control\" id=\"country\" value=\"" + country + "\" placeholder=\"country\"><br>");
                out.println("<label class=\"input-group-text\" for=\"Appearance\">Зовнішній вигляд:<label>");
                out.println("<input type=\"text\" name=\"Appearance\" class=\"form-control\" id=\"Appearance\" value=\"" + Appearance + "\" placeholder=\"Appearance\"><br>");
                out.println("<input type=\"submit\" value=\"Зберегти зміни\">");
                out.println("</form>");

                out.println("</body></html>");

            }

            //ShowData(out, statement, selectAll,true);//вивід з промальованими кнопками Update Delete
//            statement.close();
//            connection.close();
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
//            Statement statement=connection.createStatement();
            //отримати параметри,які мають бути передані з форми в updateBloknot.jsp
            Integer id=Integer.parseInt(request.getParameter("id"));
            String Manufacturer=request.getParameter("Manufacturer");
            String nameBloknot=request.getParameter("NameBloknot");
            Integer count=Integer.parseInt(request.getParameter("countPages"));
            String coverType=request.getParameter("coverType");
            String country=request.getParameter("country");
            String appearance=request.getParameter("Appearance");
            //в запиті встановлюємо отримані параметри
            String updateBloknot="UPDATE Bloknot Set nameManufacturer=?,NameBloknot=?," +
                    "countPages=?,CoverType=?,Country=?,Appearance=? " +
                    "WHERE id = ?";
            //"VALUES ('"+Manufacturer+"','"+nameBloknot+"',"+count+",'"+coverType+"','"+country+"','"+appearance+"')";
            PreparedStatement statement=connection.prepareStatement(updateBloknot);
            statement.setString(1,Manufacturer);
            statement.setString(2,nameBloknot);
            statement.setInt(3,count);
            statement.setString(4,coverType);
            statement.setString(5,country);
            statement.setString(6,appearance);
            statement.setInt(7,id);
            int rows=statement.executeUpdate();
            //Для виведення
            //---------------------------------------------------
            if (rows!=0){
            Statement statementShow=connection.createStatement();
            out.println("<h3 color=\"green\">В таблицю змінено рядків: "+rows+" </h3><br>");
            String selectAll="SELECT id, namemanufacturer, namebloknot, countpages, covertype, country, appearance\n" +
                    "\tFROM bloknot;";
            DataServlet.ShowData(out, statementShow, selectAll,true);
            }else {
                out.println("<a href=\"index.jsp\">Повернутися</a><br>");
            }
            //--------------------------------------------------
            statement.close();
            connection.close();
        } catch (SQLException e) {
            out.println(e.getMessage());
        }
    }
}
