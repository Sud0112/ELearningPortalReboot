<%-- 
    Document   : index
    Created on : 15-Jan-2021, 8:32:33 pm
    Author     : Sudhanshu D
--%>
<%@page import="com.asas.bank.database.DatabaseOperations"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="com.asas.bank.database.JDBCConnection"%>
<%@page import="com.asas.bank.model.AccountModel"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html  >
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Bank of Amrita</title>
        <link rel="icon" type="image/png" sizes="16x16" href="images/favicon-16x16.png">
        <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">

        <script
        src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
    </head>
    <body>
        <div class="row">
            <jsp:include page="header.jsp" />
        </div>
        <%! AccountModel ac;%>

        <%
            String url = "jdbc:mysql://localhost:3306/";
            String dbName = "ASASBank";
            String driver = "com.mysql.cj.jdbc.Driver";
            String userName = "root";
            String password = "";

            Class.forName(driver);

            Connection conn;
            conn = DriverManager.getConnection(url + dbName, userName, password);

            ac = (AccountModel) session.getAttribute("userDetails");
            String account_no = ac.getAccount_no();

            DatabaseOperations operations = new DatabaseOperations();
            ac = operations.getAccountDetails(conn, account_no);
        %>
        <div class="container-fullwidth">
            <div class="row" style="margin-top: 50px;">
                <div class="alert alert-success col-md-4 col-md-offset-4"
                     role="alert">
                    <h4 class="alert-heading">Success!</h4>
                    <p>
                        <strong>Money Transfer Successful!</strong>
                    </p>
                    <p class="mb-0">
                        <b>Account Number: </b>
                        <%=ac.getAccount_no()%>
                    </p>
                    <p class="mb-0">
                        <b>Balance Amount: </b>
                        <%=ac.getAmount()%>
                    </p>
                </div>
            </div>
            <div class="row" style="margin-top: 50px;">
                <jsp:include page="footer.jsp"></jsp:include>
            </div>
        </div>
    </body>
</html>