<%-- 
    Document   : signup
    Created on : Nov 4, 2021, 1:40:35 AM
    Author     : chinh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@include file="nav/header.jsp" %>
    <body>
        <%@include file="nav/nav.jsp" %>

        <!-- Breadcrumbs -->
        <div class="breadcrumbs">
            <div class="container">
                <div class="row">
                    <div class="col-12">
                        <div class="bread-inner">
                            <ul class="bread-list">
                                <li><a href=".">Home<i class="ti-arrow-right"></i></a></li>
                                <li class="active"><a href="signup">Sign Up</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- End Breadcrumbs -->

        <!-- Start SignUp -->
        <section id="contact-us" class="contact-us section">
            <div class="container">
                <div class="contact-head">
                    <div class="row">
                        <div class="col-lg-12 col-12">
                            <div class="form-main">
                                <div class="title">
                                    <h4>Sign Up</h4>
                                    <h3>Please fill below fields</h3>
                                    <h5><%=request.getAttribute("message") %></h5>
                                </div>
                                <form class="form" method="post" action="">
                                    <div class="row">
                                        <div class="col-lg-6 col-12">
                                            <div class="form-group">
                                                <label>Your Name<span>*</span></label>
                                                <input name="name" type="text" placeholder="" value="<%=request.getAttribute("name") %>" required>
                                            </div>
                                        </div>
                                        <div class="col-lg-6 col-12">
                                            <div class="form-group">
                                                <label>Your phone<span>*</span></label>
                                                <input name="phone" type="text" placeholder="" value="<%=request.getAttribute("phone") %>" required>
                                            </div>
                                        </div>
                                        <div class="col-lg-12 col-12">
                                            <div class="form-group">
                                                <label>Your Address<span>*</span></label>
                                                <input name="address" type="text" placeholder="" value="<%=request.getAttribute("address") %>" required>
                                            </div>	
                                        </div>
                                        <div class="col-lg-12 col-12">
                                            <div class="form-group">
                                                <label>Username<span>*</span></label>
                                                <input name="username" type="text" placeholder="" value="<%=request.getAttribute("username") %>" required>
                                            </div>	
                                        </div>
                                        <div class="col-lg-12 col-12">
                                            <div class="form-group">
                                                <label>Password<span>*</span></label>
                                                <input name="password" type="password" placeholder="" value="" required>
                                            </div>	
                                        </div>
                                        <div class="col-lg-12 col-12">
                                            <div class="form-group">
                                                <label>Confirmation<span>*</span></label>
                                                <input name="confirmation" type="password" placeholder="" value="" required>
                                            </div>	
                                        </div>
                                        <div class="col-4"></div>
                                        <div class="col-2">
                                            <div class="form-group button">
                                                <input type="submit" name="submit" class="btn" value="Sign Up">
                                            </div>
                                        </div>
                                        <div class="col-2">
                                            <div class="form-group button">
                                                <button type="reset" class="btn ">Reset</button>
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
        <!--/ End SignUp -->

        <%@include file="nav/footer.jsp" %>
    </body>
</html>
