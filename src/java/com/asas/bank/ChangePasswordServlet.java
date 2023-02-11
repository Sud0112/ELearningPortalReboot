//    Document   : index
//    Created on : 15-Jan-2021, 8:32:33 pm
//    Author     : DELL
package com.asas.bank;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.WebServlet;

@WebServlet("/ChangePasswordServlet")
public class ChangePasswordServlet extends HttpServlet {

    String account_no, username, userPassword, newPassword;
    Connection conn;
    Statement stmt;
    boolean pass_wrong = false;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        account_no = request.getParameter("account_no");
        username = request.getParameter("username");
        userPassword = request.getParameter("oldPassword");
        newPassword = request.getParameter("password");

        System.out.println("\nDebug");
        System.out.println(account_no);
        System.out.println(username);
        System.out.println(userPassword);
        System.out.println(newPassword);

        try {

            String url = "jdbc:mysql://localhost:3306/";
            String dbName = "ASASBank";
            String driver = "com.mysql.cj.jdbc.Driver";
            String userName = "root";
            String password = "";

            try {
                Class.forName(driver);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(DepositServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            Connection conn;
            conn = DriverManager.getConnection(url + dbName, userName, password);

            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM account WHERE id='" + account_no + "' AND username='" + username
                    + "' and password='" + userPassword + "'");

            if (!rs.isBeforeFirst()) {
                request.setAttribute("isPassOK", "No");
                RequestDispatcher rd = request.getRequestDispatcher("change_password.jsp");
                rd.forward(request, response);
            } else {
                System.out.println("\nDebug");
                System.out.println("Inside ASAS DB");

                PreparedStatement ps = conn.prepareStatement("UPDATE account SET password=? WHERE id= ?");
                ps.setString(1, newPassword);
                ps.setString(2, account_no);
                ps.executeUpdate();

                conn.close();

                RequestDispatcher rd = request.getRequestDispatcher("ChangePassword_process.jsp");
                rd.forward(request, response);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
