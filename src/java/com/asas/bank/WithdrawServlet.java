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

import com.asas.bank.database.JDBCConnection;

public class WithdrawServlet extends HttpServlet {

    String account_no, username, password;
    Connection conn;
    Statement stmt;
    boolean pass_wrong = false;
    int current_amount, withdraw_amount;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        account_no = request.getParameter("account_no");
        username = request.getParameter("username");
        password = request.getParameter("password");
        withdraw_amount = Integer.parseInt(request.getParameter("amount"));

        try {
            JDBCConnection connect = new JDBCConnection();
            conn = connect.getConnection();

            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM account WHERE id='" + account_no + "' and username='"
                    + username + "' and password='" + password + "'");

            if (!rs.isBeforeFirst()) {
                request.setAttribute("isPassOK", "No");
                RequestDispatcher rd = request.getRequestDispatcher("withdraw.jsp");
                rd.forward(request, response);
            } else {
                System.out.println("\nDebug");
                System.out.println("Inside ASAS DB");
                ResultSet rs1 = stmt.executeQuery("SELECT * FROM amount WHERE id ='" + account_no + "'");

                while (rs1.next()) {
                    current_amount = rs1.getInt(2);

                    System.out.println(current_amount);
                }

                if (current_amount >= withdraw_amount) {
                    current_amount -= withdraw_amount;

                    PreparedStatement ps = conn.prepareStatement("UPDATE amount SET amount=? WHERE id= ?");
                    ps.setInt(1, current_amount);
                    ps.setString(2, account_no);
                    ps.executeUpdate();

                    conn.close();

                    RequestDispatcher rd = request.getRequestDispatcher("Withdraw_process.jsp");
                    rd.forward(request, response);
                } else {
                    conn.close();
                    request.setAttribute("EnoughMoney", "No");
                    RequestDispatcher rd = request.getRequestDispatcher("withdraw.jsp");
                    rd.forward(request, response);
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
