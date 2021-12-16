<%-- 
    Document   : login
    Created on : Oct 30, 2021, 6:14:44 PM
    Author     : Chinh
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
                                <li class="active"><a href="login">Login</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- End Breadcrumbs -->

        <%
            String message = (String) request.getAttribute("message");
        %>
        
        <!-- Start Login -->
        <section id="contact-us" class="contact-us section">
            <div class="container">
                <div class="contact-head">
                    <div class="row">
                        <div class="col-lg-12 col-12">
                            <div class="form-main">
                                <div class="title">
                                    <h4>Login</h4>
                                    <h3>Enter your username and password</h3>
                                    <% if(message!=null) { %>
                                    <br><h5><%=message %></h5>
                                    <%} %>
                                </div>
                                <form class="form" method="post" action="">
                                    <div class="row">
                                        <div class="col-lg-12 col-12">
                                            <div class="form-group">
                                                <label>Username<span>*</span></label>
                                                <input name="username" type="text" placeholder="">
                                            </div>	
                                        </div>
                                        <div class="col-lg-12 col-12">
                                            <div class="form-group">
                                                <label>Password<span>*</span></label>
                                                <input name="password" type="password" placeholder="">
                                            </div>	
                                        </div>
                                        <div class="col-4"></div>
                                        <div class="col-2">
                                            <div class="form-group button">
                                                <input type="submit" name="submit" class="btn" value="Login"/>

                                            </div>
                                        </div>
                                        <div class="col-2">
                                            <div class="form-group button">
                                                <input type="reset" class="btn" value="Reset" />
                                            </div>
                                        </div>
                                        <div class="col-4"></div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!--/ End Login -->

        <%@include file="nav/footer.jsp" %>
    </body>
</html>