<%-- 
    Document   : index
    Created on : 14-Jan-2021, 9:37:38 pm
    Author     : Sudhanshu D
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html  >
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Bank of Amrita</title>
        <link rel="icon" type="image/png" sizes="16x16" href="images/favicon-16x16.png">
        <link rel="stylesheet" type="text/css" href="css/main_css.css">
        <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">

        <script
        src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>

    </head>
    <body>
        <div class="row">
            <jsp:include page="header.jsp" />
        </div>
        <div class="container-fullwidth" style="margin-top: 0 px">
            <div class="row">
                <div class="col-md-12">
                    <div id="carousel-example-generic" class="carousel slide"
                         data-ride="carousel">

                        <ol class="carousel-indicators">
                            <li data-target="#carousel-example-generic" data-slide-to="0"
                                class="active"></li>
                            <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                            <li data-target="#carousel-example-generic" data-slide-to="2"></li>
                        </ol>

                        <div class="carousel-inner">
                            <div class="item active">
                                <img src="image/Carousel1.jpg" alt="First slide">

                            </div>
                            <div class="item">
                                <img src="image/Carousel2.jpg" alt="Second slide">

                            </div>
                            <div class="item">
                                <img src="image/Carousel3.jpg" alt="Third slide">

                            </div>
                        </div>

                        <a class="left carousel-control" href="#carousel-example-generic"
                           data-slide="prev"> <span
                                class="glyphicon glyphicon-chevron-left"></span></a><a
                            class="right carousel-control" href="#carousel-example-generic"
                            data-slide="next"><span
                                class="glyphicon glyphicon-chevron-right"> </span> </a>


                    </div>
                </div>
            </div>
            <div class="row">
                <div class="extras" style="margin-bottom: -155px;">
                    <div class="col-md-4 about-institute">
                        <h2 class="text-center text-medium">
                            Our<br>Institute
                        </h2>
                        <p class="col-md-6 col-md-offset-3 text-center scheme-text">Amrita Vishwa Vidyapeetham, Deemed to be University is one of the top ranked private Universities in India, with it's accolades rising every year.</p>
                        <div class="scheme-button col-md-4 col-md-offset-4">
                            <a type="button" class="btn btn-default btn-lg" href="https://www.amrita.edu/"
                               style="border-radius: 24px;">Visit Website</a>
                        </div>
                    </div>
                    <div class="col-md-4 scheme-type-2">
                        <h2 class="text-center text-medium">
                            Our<br>Campus
                        </h2>
                        <p class="col-md-6 col-md-offset-3 text-center scheme-text">Mysore Campus, or the School of Arts and Sciences is one of the smallest of the Amrita Vishwa Vidyapeetham fleet. However, the students are top notch, and we grow every day.</p>   
                    </div>
                    <div class="col-md-4 scheme-type-3">
                        <h2 class="text-center text-medium">
                            Our<br>Team
                        </h2>
                        <p class="col-md-6 col-md-offset-3 text-center scheme-text">Front End Dev : Gunashree O </br> Database Architect : Arshitha S A </br>Front End Dev : Brinda B </br>Back End Dev : Sudhanshu D </br>Back End Dev :  Abhishek M B</br>Chief Motivator : Shri Vinayak Hegde</p>
                    </div>
                </div>
            </div>
            <div class="row" style="margin-top: 50px;">
                <jsp:include page="footer.jsp"></jsp:include>
            </div>
        </div>


    </body>
</html>