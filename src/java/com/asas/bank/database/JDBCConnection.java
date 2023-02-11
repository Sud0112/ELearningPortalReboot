//    Document   : index
//    Created on : 16-Jan-2021, 3:21:19 pm
//    Author     : arshitha's HP
package com.asas.bank.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnection {

    private Connection connection = null;

    public Connection getConnection() throws SQLException {

        String url = "jdbc:mysql://localhost:3306/";
        String dbName = "ASASBank";
        String driver = "com.mysql.cj.jdbc.Driver";
        String userName = "root";
        String password = "";

        try {

            Class.forName(driver);
            Connection conn;
            conn = DriverManager.getConnection(url + dbName, userName, password);
            return conn;

        } catch (ClassNotFoundException e) {
            System.out.println("\nDebug");
            System.out.println("Where is your JDBC Driver?");
            e.printStackTrace();

        }

        return connection;

    }

}
