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
import java.sql.DriverManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TransferServlet extends HttpServlet {

    String account_no, username, target_acc_no, userPassword;
    Connection conn;
    Statement stmt, stmt2;
    boolean pass_wrong = false;
    int own_amount, transfer_amount, recipient_amount;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        account_no = request.getParameter("account_no");
        username = request.getParameter("username");
        target_acc_no = request.getParameter("target_acc_no");
        userPassword = request.getParameter("password");
        transfer_amount = Integer.parseInt(request.getParameter("amount"));

        try {
            String url = "jdbc:mysql://localhost:3306/";
            String dbName = "ASASBank";
            String driver = "com.mysql.cj.jdbc.Driver";
            String userName = "root";
            String password = "";

            try {
                Class.forName(driver);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(TransferServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            Connection conn;
            conn = DriverManager.getConnection(url + dbName, userName, password);

            stmt = conn.createStatement();
            stmt2 = conn.createStatement();

            ResultSet rsOwn = stmt.executeQuery("SELECT * FROM account WHERE id='" + account_no + "' and username='"
                    + username + "' and password='" + userPassword + "'");

            ResultSet rstTarget = stmt2.executeQuery("SELECT * FROM account WHERE id='" + target_acc_no + "'");

            if (!rsOwn.isBeforeFirst() && !rstTarget.isBeforeFirst()) {
                request.setAttribute("isPassOK", "No");
                RequestDispatcher rd = request.getRequestDispatcher("transfer.jsp");
                rd.forward(request, response);
            } else {
                System.out.println("\nDebug");
                System.out.println("Inside ASAS DB");
                ResultSet rs1 = stmt.executeQuery("SELECT * FROM amount WHERE id ='" + account_no + "'");

                while (rs1.next()) {
                    own_amount = rs1.getInt(2);
                    System.out.println(own_amount);
                }

                if (own_amount >= transfer_amount) {
                    own_amount -= transfer_amount;

                    ResultSet rs2 = stmt.executeQuery("SELECT * FROM amount WHERE id ='" + target_acc_no + "'");

                    while (rs2.next()) {
                        recipient_amount = rs2.getInt(2);
                        System.out.println(recipient_amount);
                    }

                    recipient_amount += transfer_amount;

                    PreparedStatement ps = conn.prepareStatement("UPDATE amount SET amount=? WHERE id= ?");
                    ps.setInt(1, own_amount);
                    ps.setString(2, account_no);
                    ps.executeUpdate();

                    PreparedStatement ps1 = conn.prepareStatement("UPDATE amount SET amount=? WHERE id= ?");
                    ps1.setInt(1, recipient_amount);
                    ps1.setString(2, target_acc_no);
                    ps1.executeUpdate();

                    conn.close();

                    RequestDispatcher req = request.getRequestDispatcher("transfer_process.jsp");
                    req.forward(request, response);

                } else {
                    conn.close();
                    request.setAttribute("EnoughMoney", "No");
                    RequestDispatcher rd = request.getRequestDispatcher("transfer.jsp");
                    rd.forward(request, response);
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
