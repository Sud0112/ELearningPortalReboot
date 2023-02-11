//    Document   : index
//    Created on : 16-Jan-2021, 3:51:27 pm
//    Author     : arshitha's HP
package com.asas.bank.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.asas.bank.model.AccountModel;

public class DatabaseOperations {

    Connection conn;
    int count1, count2;

    public boolean insertAccountDetails(AccountModel model) throws Exception {

        try {

            String url = "jdbc:mysql://localhost:3306/";
            String dbName = "ASASBank";
            String driver = "com.mysql.cj.jdbc.Driver";
            String userName = "root";
            String password = "";

            Class.forName(driver);
            Connection conn;
            conn = DriverManager.getConnection(url + dbName, userName, password);

            PreparedStatement ps1 = conn.prepareStatement("INSERT INTO account VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)");
            ps1.setString(1, model.getAccount_no());
            ps1.setString(2, model.getFirst_name());
            ps1.setString(3, model.getLast_name());
            ps1.setString(4, model.getAddress());
            ps1.setString(5, model.getCity());
            ps1.setString(6, model.getBranch());
            ps1.setString(7, model.getZip());
            ps1.setString(8, model.getUsername());
            ps1.setString(9, model.getPassword());
            ps1.setString(10, model.getPhone_number());
            ps1.setString(11, model.getEmail());
            ps1.setString(12, model.getAccount_type());
            ps1.setString(13, model.getReg_date());

            count1 = ps1.executeUpdate();

            System.out.println("\nDebug");
            System.out.println("Inserted " + count1 + " rows");

            PreparedStatement ps2 = conn.prepareStatement("INSERT INTO amount VALUES(?,?)");
            ps2.setString(1, model.getAccount_no());
            ps2.setInt(2, model.getAmount());

            count2 = ps2.executeUpdate();

            System.out.println("\nDebug");
            System.out.println("Inserted " + count2 + " rows");

            conn.close();

        } catch (SQLException e) {
            System.out.println(e);
            e.printStackTrace();
        }

        return ((count1 > 0) && (count2 > 0));
    }

    public AccountModel getAccountDetails(Connection conn, String account_no) throws Exception {
        AccountModel am = new AccountModel();

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM account WHERE id ='" + account_no + "'");
        while (rs.next()) {

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
        }
        ResultSet rs1 = stmt.executeQuery("SELECT * FROM amount WHERE id ='" + am.getAccount_no() + "'");
        while (rs1.next()) {
            am.setAmount(rs1.getInt(2));
        }
        return am;
    }

}
