<%-- 
    Document   : logout
    Created on : Nov 8, 2021, 2:41:52 AM
    Author     : chinh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@include file="nav/header.jsp" %>
    <body class="js">
        <%@include file="nav/nav.jsp" %>

        <!-- Breadcrumbs -->
        <div class="breadcrumbs">
            <div class="container">
                <div class="row">
                    <div class="col-12">
                        <div class="bread-inner">
                            <ul class="bread-list">
                                <li><a href=".">Home<i class="ti-arrow-right"></i></a></li>
                                <li class="active"><a href="logout">Log out</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- End Breadcrumbs -->
        <div class="shopping-cart section">
            <div class="container">
                <div class="row">
                    <div class="col-lg-4 col-md-4 col-12">

                    </div>
                    <div class="col-lg-4 col-md-4 col-12">
                        <form action="" method="POST">
                            <p><h3>Your cart will be deleted. Continue?</h3></p>
                            <br>
                            <input type="submit" name="submit" value="Log out" class="btn">
                            <br><br>
                            <button class="btn"><a href=".">Back to homepage</a></button>
                        </form>
                    </div>
                    <div class="col-lg-4 col-md-4 col-12">

                    </div>

                </div>
            </div>
        </div>


        <%@include file="nav/footer.jsp" %>
    </body>
</html>
