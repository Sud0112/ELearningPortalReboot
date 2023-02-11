<%-- 
    Document   : index
    Created on : 15-Jan-2021, 4:21:18 pm
    Author     : Sudhanshu D
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html  >
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Sign In</title>
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

        <div class="container-fullwidth">
            <div class="row">
                <div class="jumbotron col-md-6 col-md-offset-3"
                     style="margin-top: 50px">
                    <h1 style="margin-bottom: 30px" class="col-md-10 col-md-offset-1">Sign
                        in to your account</h1>
                    <div class="row">
                        <div class="col-md-6 col-md-offset-2">
                            <form method="post" action="LoginServlet">
                                <div class="form-group">
                                    <label for="email">UserName</label> <input type="text"
                                                                               name="UserName" required class="form-control" id="account_no"
                                                                               placeholder="UserName">
                                </div>
                                <div class="form-group">
                                    <label for="password">Password*</label> <input type="password"
                                                                                   name="password" required class="form-control" id="password"
                                                                                   placeholder="Password">
                                </div>
                                <!--							<div class="checkbox">
                                                                                                <label> <input name="remember" type="checkbox"
                                                                                                        value="Remember Me">Remember Me
                                                                                                </label>
                                                                                        </div>-->
                                <%
                                    String isPassOK = (String) request.getAttribute("isPassOK");
                                    if (isPassOK != null && isPassOK.equals("No")) {
                                %>
                                <div class="alert alert-danger" role="alert">
                                    <strong>Account No/Password does not match.</strong>
                                </div>
                                <%
                                } else 
                                %>
                                <input type="submit" class="btn btn-primary btn-lg"
                                       value="Sign
                                       to your account"></input>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row" style="margin-top: 50px;">
                <jsp:include page="footer.jsp"></jsp:include>
            </div>
        </div>
    </body>
</html>