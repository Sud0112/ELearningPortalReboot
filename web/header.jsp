<%-- 
    Document   : index
    Created on : 15-Jan-2021, 8:32:33 pm
    Author     : Sudhanshu D
--%>
<%@page import="com.asas.bank.model.AccountModel"%>
<%@page import="javax.servlet.http.HttpSession"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html  >
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <link rel="stylesheet" type="text/css" href="css/header_css.css">
    </head>
    <div class="container-fullwidth">
        <div class="row hidden-xs topper">
            <div class="col-xs-7 col-sm-7">
                <a href="index.jsp"><img src="image/bankofAmritaHeader.png" class="" height="100px" ></a>
            </div>
        </div>
        <div class="row">
            <nav class="navbar navbar-inverse" role="navigation" style="margin-bottom: 0px;">
                <div class="container">
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
                            <span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
                        </button>
                        <a class="navbar-brand visible-xs-inline-block nav-logo" href="/"><img src="/images/logo-dark-inset.png" class="img-responsive" alt=""></a>
                    </div>

                    <%!AccountModel ac = null;%>

                    <%
                        ac = (AccountModel) session.getAttribute("userDetails");
                    %>

                    <div class="collapse navbar-collapse navbar-ex1-collapse">
                        <ul class="nav navbar-nav js-nav-add-active-class">
                            <li><a href="index.jsp">Home</a></li>
                            <li><a>Banking Functions: </a></li>
                                <%
                                    if (ac == null) {
                                %>
                            <li><a href="create_account.jsp">Create New Account</a></li>
                                <%
                                    }
                                %>
                            <li><a href="deposit.jsp">Deposit</a></li>
                            <li><a href="withdraw.jsp">Withdraw</a></li>
                            <li><a href="transfer.jsp">Transfer</a></li>
                            <li><a href="close_account.jsp">Close Account</li>
                        </ul>
                        <%
                            if (ac != null) {
                        %>
                        <ul class="nav navbar-nav navbar-right">
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown"> 
                                    <span class="glyphicon glyphicon-user"></span>
                                    <strong><%=ac.getUsername()%></strong> 
                                    <span class="glyphicon glyphicon-chevron-down"></span>
                                </a>
                                <ul class="dropdown-menu">
                                    <li>
                                        <div class="navbar-login">
                                            <div class="row">
                                                <div class="col-md-4">
                                                    <p class="text-center">
                                                        <span class="glyphicon glyphicon-user icon-size"></span>
                                                    </p>
                                                </div>
                                                <div class="col-md-8">
                                                    <p class="text-left">
                                                        <strong><%=ac.getFirst_name() + " " + ac.getLast_name()%></strong>
                                                    </p>
                                                    <p class="text-left small"><%=ac.getEmail()%>
                                                    </p>
                                                    <p class="text-left">
                                                        <a href="profile.jsp" class="btn btn-primary btn-block btn-sm">Profile</a>
                                                    </p>
                                                </div>
                                            </div>
                                        </div>
                                    </li>
                                    <li class="divider navbar-login-session-bg"></li>
                                        <%
                                            if (ac.getUsername().equals("admin")) {
                                        %>
                                        <%
                                        } else {
                                        %>
                                    <li><a href="change_password.jsp">Change Password<span class="glyphicon glyphicon-cog pull-right"></span></a></li>
                                            <%
                                                }
                                            %>
                                    <li class="divider"></li>
                                    <li><a href="logout.jsp">Sign Out<span class="glyphicon glyphicon-log-out pull-right"></span></a></li>
                                </ul></li>
                        </ul>
                        <%
                        } else {
                        %>
                        <ul class="nav navbar-nav navbar-right hidden-xs">
                            <a type="button" class="navbar-btn btn btn-primary" am-latosans="bold" href="login.jsp" style="background-color: #D24720">Login</a>
                        </ul>
                        <%
                            }
                        %>
                    </div>
                </div>
            </nav>
        </div>
    </div>
</html>