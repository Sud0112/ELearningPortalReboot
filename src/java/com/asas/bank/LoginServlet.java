//    Document   : index
//    Created on : 15-Jan-2021, 8:32:33 pm
//    Author     : DELL
package com.asas.bank;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.asas.bank.model.AccountModel;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginServlet extends HttpServlet {

    String UserName, userPassword;
    Connection conn;
    AccountModel am = null;
    boolean pass_wrong = false;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserName = request.getParameter("UserName");
        userPassword = request.getParameter("password");

        System.out.println(UserName);
        System.out.println(userPassword);

        try {
            String url = "jdbc:mysql://localhost:3306/";
            String dbName = "ASASBank";
            String driver = "com.mysql.cj.jdbc.Driver";
            String userName = "root";
            String password = "";

            try {
                Class.forName(driver);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            Connection conn;
            conn = DriverManager.getConnection(url + dbName, userName, password);

            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(
                    "SELECT * FROM account WHERE username ='" + UserName + "'" + "and password='" + userPassword + "'");

            if (!rs.isBeforeFirst()) {
                request.setAttribute("isPassOK", "No");
                RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
                rd.forward(request, response);
            } else {
                while (rs.next()) {
                    pass_wrong = true;
                    am = new AccountModel();
                    am.setAccount_no(rs.getString(1));
                    am.setFirst_name(rs.getString(2));
                    am.setLast_name(rs.getString(3));
                    am.setAddress(rs.getString(4));
                    am.setCity(rs.getString(5));
                    am.setBranch(rs.getString(6));
                    am.setZip(rs.getString(7));
                    am.setUsername(rs.getString(8));
                    am.setPassword(rs.getString(9));
                    am.setPhone_number(rs.getString(10));
                    am.setEmail(rs.getString(11));
                    am.setAccount_type(rs.getString(12));
                    am.setReg_date(rs.getString(13));

                    ResultSet rs1 = stmt.executeQuery("SELECT * FROM amount WHERE id ='" + am.getAccount_no() + "'");

                    while (rs1.next()) {
                        am.setAmount(rs1.getInt(2));
                    }

                    HttpSession session = request.getSession();
                    session.setAttribute("userDetails", am);

                    RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
                    rd.forward(request, response);

                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
