/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jaybuddhdev
 */
public class RegistrationServlet extends HttpServlet {

     @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        //PreparedStatement ps=null;
        // Connection con=null;
        try {
            Class.forName(DatabaseConstants.driverName);
            Connection con = DriverManager.getConnection(DatabaseConstants.connectionString, DatabaseConstants.UserName, DatabaseConstants.Password);

            String a = request.getParameter("fname");
            String b = request.getParameter("lname");
            String c = request.getParameter("age");
            String d = request.getParameter("date");
            String e = request.getParameter("birthcertificate");
            String f = request.getParameter("email");
            String g = request.getParameter("pass");
            String h = request.getParameter("mno");
            String password=request.getParameter("password");

            String query = "insert into users(fname,lname,age,date,birthcertificate,email,pass,phone_no) values (?,?,?,?,?,?,?,?)";

            try {
                PreparedStatement ps = con.prepareStatement(query);
                ps.setString(1, a);
                ps.setString(2, b);
                ps.setString(3, c);
                ps.setString(4, d);
                ps.setString(5, e);
                ps.setString(6, f);
                ps.setString(7, password);
                ps.setString(8, h);

                int rows_inserted = ps.executeUpdate();
                if (rows_inserted == 1) {
                    response.sendRedirect("login.html");
                }
//                out.println("<html><head></head><body onload=\"alert('Registered Successfully.Now Log In')\"></body></html>");

//                RequestDispatcher rd = request.getRequestDispatcher("login.html");
//                rd.include(request, response);
                out.println("<script>alert('Problem Occured During registration! register again.');</script>");
                request.getRequestDispatcher("registration.html").include(request, response);
            } catch (SQLException ex) {
                System.out.println("Exception 1:" + ex.getMessage());
            }

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
    }
}
