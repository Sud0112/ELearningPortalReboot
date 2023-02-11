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

public class DepositServlet extends HttpServlet {

    String account_no, username, userPassword;
    Connection conn;
    Statement stmt;
    boolean pass_wrong = false;
    int current_amount, deposit_amount;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        account_no = request.getParameter("account_no");
        username = request.getParameter("username");
        userPassword = request.getParameter("password");
        deposit_amount = Integer.parseInt(request.getParameter("amount"));

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
                RequestDispatcher rd = request.getRequestDispatcher("deposit.jsp");
                rd.forward(request, response);
            } else {
                System.out.println("\nDebug");
                System.out.println("Inside ASAS DB");
                ResultSet rs1 = stmt.executeQuery("SELECT * FROM amount WHERE id='" + account_no + "'");

                while (rs1.next()) {
                    current_amount = rs1.getInt(2);

                    System.out.println(current_amount);
                }

                current_amount += deposit_amount;

                PreparedStatement ps = conn.prepareStatement("UPDATE amount SET amount=? WHERE id= ?");
                ps.setInt(1, current_amount);
                ps.setString(2, account_no);
                ps.executeUpdate();

                conn.close();

                RequestDispatcher rd = request.getRequestDispatcher("Deposit_process.jsp");
                rd.forward(request, response);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
