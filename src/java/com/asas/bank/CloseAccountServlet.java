/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asas.bank;

import com.asas.bank.database.JDBCConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Sudhanshu D
 */
@WebServlet(name = "CloseAccountServlet", urlPatterns = {"/CloseAccountServlet"})
public class CloseAccountServlet extends HttpServlet {

    String account_no, username, password;
    Connection conn;
    Statement stmt;
    boolean pass_wrong = false;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        account_no = request.getParameter("account_no");
        username = request.getParameter("username");
        password = request.getParameter("password");

        try {
            JDBCConnection connect = new JDBCConnection();
            conn = connect.getConnection();

            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM account WHERE id='" + account_no + "' and username='"
                    + username + "' and password='" + password + "'");

            if (!rs.isBeforeFirst()) {
                request.setAttribute("isPassOK", "No");
                RequestDispatcher rd = request.getRequestDispatcher("close_account.jsp");
                rd.forward(request, response);
            } else {
                System.out.println("\nDebug");
                System.out.println("Inside ASAS DB");
                stmt.addBatch("DELETE FROM amount WHERE id ='" + account_no + "'");
                stmt.addBatch("DELETE FROM account WHERE id ='" + account_no + "'");

                int[] recordsAffected = stmt.executeBatch();

                System.out.println(recordsAffected);

                conn.close();

                RequestDispatcher rd = request.getRequestDispatcher("closeAccount_process.jsp");
                rd.forward(request, response);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
