<%-- 
    Document   : product
    Created on : Nov 4, 2021, 1:36:43 AM
    Author     : chinh
--%>

<%@page import="Entity.Product"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@include file="nav/header.jsp" %>

    <body class="js">
        <%@include file="nav/nav.jsp" %>
        <%
            String cateName = (String) session.getAttribute("cateName");
            Product prod = (Product) session.getAttribute("prod");
        %>

        <!-- Breadcrumbs -->
        <div class="breadcrumbs">
            <div class="container">
                <div class="row">
                    <div class="col-12">
                        <div class="bread-inner">
                            <ul class="bread-list">
                                <li><a href=".">Home<i class="ti-arrow-right"></i></a></li>
                                <li class="active"><a href="product?pid=<%=prod.getPid()%>"><%=prod.getPname()%></a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- End Breadcrumbs -->

        <!-- Start Product -->
        <section class="shopping-cart contact-us section">
            <div class="container">
                <div class="row">
                    <div class="col-12">
                        <div class="total-amount">
                            <div class="row">
                                <div class="col-lg-5 col-md-5 col-12">
                                    <div class="left">
                                        <div class="coupon">
                                            <div class="image" >
                                                <img src="asset/images/product/<%=prod.getImage()%>" alt="#">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-3 col-md-3 col-12">
                                    <div class="center">
                                        <div class="blog-single-main">
                                            <div class="title">
                                                <h4><%=cateName%></h4>
                                                <h3><%=prod.getPname()%></h3>
                                            </div>
                                            <p class="product-des"><%=prod.getDescription()%></p>
                                        </div>

                                    </div>
                                </div>
                                <div class="col-lg-4 col-md-4 col-12">
                                    <div class="right">
                                        <ul>
                                            <li>Stock<span><%=prod.getQuantity()%></span></li>
                                            <li>Price<span>$<%=prod.getPrice()%></span></li>
                                            <% if (privilege != 1) { %>
                                            <form action="addtocart?pid=<%=prod.getPid() %>" method="POST">
                                            <li class="last">Buy<span>
                                                    <input type="number" name="quantity" value="1" max="<%=prod.getQuantity()%>" style="width:5em; text-align: center"/>
                                                </span></li></ul>
                                                <div class="button5">
                                            <input type="submit" name="submit" class="btn" value="Add to cart"/>
                                        </div>
                                                </form>
                                            <%} else { %>
                                            </ul>
                                            <%} %>
                                        <% if (privilege == 1) { %>
                                        <div class="button5">
                                            <a href="#" class="btn">Edit</a>
                                        </div>
                                        <br>
                                        <div class="button5">
                                            <a href="#" class="btn">Delete</a>
                                        </div>
                                        <%} else { %>
                                        
                                        <%} %>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!--/ End Product -->
        <%@include file="nav/footer.jsp" %>
    </body>
</html>
