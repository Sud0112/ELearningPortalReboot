<%-- 
    Document   : index
    Created on : 15-Jan-2021, 8:32:33 pm
    Author     : Sudhanshu D
--%>
<%@page import="com.asas.bank.model.AccountModel"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html  >
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Deposit</title>
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
        <div class="container-fullwidth">
            <%
                AccountModel ac = null;
            %>
            <%
                ac = (AccountModel) session.getAttribute("userDetails");
                if (ac != null) {
            %>
            <div class="row" style="margin-top: 50px;">
                <div class="col-md-4 col-md-offset-4">
                    <form role="form" method="post" action="DepositServlet">
                        <h2>Deposit Form</h2>
                        <div class="col-md-12">
                            <hr class="colorgraph">
                        </div>
                        <label class="col-md-4 control-label">Account No</label>
                        <div class="col-sm-8 form-group">
                            <input type="text" required placeholder="Enter Account No"
                                   class="form-control" name="account_no"
                                   value="<%=ac.getAccount_no()%>">
                        </div>
                        <label class="col-md-4 control-label">User Name</label>
                        <div class="col-sm-8 form-group">
                            <input type="text" required placeholder="Enter User Name"
                                   class="form-control" name="username"
                                   value="<%=ac.getUsername()%>">
                        </div>

                        <label class="col-md-4 control-label">Password</label>
                        <div class="col-sm-8 form-group">
                            <input type="password" required placeholder="Enter Password"
                                   class="form-control" name="password">
                        </div>

                        <label class="col-md-4 control-label">Amount</label>
                        <div class="col-sm-8 form-group">
                            <input type="number" required placeholder="Enter Amount"
                                   class="form-control" name="amount">
                        </div>
                        <div class="col-md-12">
                            <hr class="colorgraph">
                        </div>
                        <%
                            String isPassOK = (String) request.getAttribute("isPassOK");
                            if (isPassOK != null && isPassOK.equals("No")) {
                        %>
                        <div class="col-md-12">
                            <div class="alert alert-danger" role="alert">
                                <strong>Sorry!</strong> Password incorrect.
                            </div>
                        </div>
                        <%
                            }
                        %>
                        <div class="row col-md-10 col-md-offset-1">
                            <div class="col-xs-6 col-md-6">
                                <input type="submit" value="Submit"
                                       class="btn btn-success btn-block btn-md" tabindex="7">
                            </div>
                            <div class="col-xs-6 col-md-6">
                                <input class="btn btn-danger btn-block btn-md" type="reset"
                                       value="Reset">
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <%
            } else {
            %>
            <div class="row" style="margin-top: 150px;">
                <div class="alert alert-warning col-md-4 col-md-offset-4"
                     role="alert">
                    <strong>ERROR!</strong> Please Log In to access this Page.
                </div>
            </div>

            <%
                }
            %>

            <div class="row" style="margin-top: 50px;">
                <jsp:include page="footer.jsp"></jsp:include>
            </div>
        </div>
    </body>
</html>