<%-- 
    Document   : index
    Created on : 15-Jan-2021, 8:32:33 pm
    Author     : Sudhanshu D
--%>
<%@page import="com.asas.bank.database.DatabaseOperations"%>
<%@page import="com.asas.bank.model.AccountModel"%>
<%@page import="java.sql.Connection,java.sql.PreparedStatement"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html  >
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Bank of Amrita</title>
        <link rel="icon" type="image/png" sizes="16x16" href="images/favicon-16x16.png">
        <link rel="stylesheet" type="text/css" href="css/deposit.css">
        <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">

        <script
        src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
    </head>
    <body>
        <div class="row">
            <jsp:include page="header.jsp" />
        </div>

        <%!String account_no;
            int amount;
            boolean allRight;
            AccountModel model = new AccountModel();
            DatabaseOperations dbOperation = new DatabaseOperations();%>


        <%
            model = (AccountModel) request.getAttribute("Account_details");
            System.out.println(model);
            allRight = dbOperation.insertAccountDetails(model);
            if (allRight) {
        %>

        <div class="container-fullwidth">
            <div class="row" style="margin-top: 50px;">
                <div class="alert alert-success col-md-4 col-md-offset-4" role="alert">
                    <h4 class="alert-heading">Success!</h4>
                    <p>
                        <strong>Your Account has been created.</strong>
                    </p>
                    <p class="mb-0">
                        <b>Account Number: </b>
                        <%=model.getAccount_no()%>
                    </p>
                    <p class="mb-0">
                        <b>Amount: </b>
                        <%=model.getAmount()%>
                    </p>
                </div>
            </div>
            <%
            } else {
            %>
            <div class="container-fullwidth">
                <div class="row" style="margin-top: 50px;">
                    <div class="alert alert-danger col-md-4 col-md-offset-4" role="alert">
                        <strong>Oh snap!</strong> Something went wrong during Insertion. Check your DB.
                    </div>
                </div>
            </div>522

            <%
                }
            %>
            <div class="row" style="margin-top: 50px;">
                <jsp:include page="footer.jsp"></jsp:include>
            </div>
        </div>
    </body>
</html>