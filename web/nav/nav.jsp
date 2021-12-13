<%-- 
    Document   : nav
    Created on : Oct 28, 2021, 2:23:18 PM
    Author     : Chinh
--%>

<%@page import="Entity.Admin"%>
<%@page import="Entity.Customer"%>
<%@page import="java.util.Collections"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Entity.Product"%>
<%@page import="Entity.Category"%>
<%@page import="Entity.Shopping"%>
<%@page import="java.util.Enumeration"%>

<header class="header shop">
    <%
        //    String username = (String) session.getAttribute("username");

        int privilege = (Integer) session.getAttribute("privilege");
        int showCate = (Integer) session.getAttribute("showCate");
        double total = 0;

        if (privilege < 1) {
            total = (Double) session.getAttribute("total");
            Customer customer = (Customer) session.getAttribute("customer");
            ArrayList<Product> cart = (ArrayList) session.getAttribute("cart");
            ArrayList<Category> cate = (ArrayList) session.getAttribute("cate");

            Enumeration<Product> cart_items = Collections.enumeration(cart);
            Enumeration<Category> cate_items = Collections.enumeration(cate);

    %>
    <div class="topbar">
        <div class="container">
            <div class="row">
                <div class="col-lg-5 col-md-12 col-12">
                    <!-- Top Left -->
                    <div class="top-left">
                        <ul class="list-main">
                            <li><i class="ti-email"></i> chinhldse04423@fpt.edu.vn</li>
                        </ul>
                    </div>
                    <!--/ End Top Left -->
                </div>
                <div class="col-lg-7 col-md-12 col-12">
                    <!-- Top Right -->
                    <div class="right-content">
                        <ul class="list-main">
                            <% if (customer != null) {%>
                            <li><i class="ti-user"></i> <a href="#">Welcome, <%=customer.getCname()%></a></li>
                            <li><i class="ti-power-off"></i><a href="logout">Logout</a></li>
                                    <% } else {%>
                            <li><i class="ti-power-off"></i><a href="login">Login</a></li>
                            <li><i class="ti-face-smile"></i><a href="signup">Sign-up</a></li>
                                    <%} %>
                        </ul>
                    </div>
                    <!-- End Top Right -->
                </div>
            </div>
        </div>
    </div>

    <!-- Header -->
    <div class="middle-inner">
        <div class="container">
            <div class="row">
                <div class="col-lg-2 col-md-2 col-12">
                    <!-- Logo -->
                    <div class="logo">
                        <a href="."><img src="asset/images/logo.png" alt="SE04423"></a>
                    </div>
                    <!--/ End Logo -->
                    <!-- Search Form -->
                    <div class="search-top">
                        <div class="top-search"><a href="search"><i class="ti-search"></i></a></div>
                        <!-- Search Form -->
                        <div class="search-top">
                            <form action="" class="search-form">
                                <input type="text" placeholder="Search here..." name="search">
                                <button value="search" type="submit"><i class="ti-search"></i></button>
                            </form>
                        </div>
                        <!--/ End Search Form -->
                    </div>
                    <!--/ End Search Form -->
                    <div class="mobile-nav"></div>
                </div>
                <div class="col-lg-8 col-md-7 col-12">
                    <form action="search" method="POST">
                        <div class="search-bar-top">
                            <div class="search-bar">
                                <select name="searchCateID">
                                    <option value="" selected>All Category</option>
                                    <% while (cate_items.hasMoreElements()) {
                                            Category cate_item = cate_items.nextElement();
                                    %>
                                    <option value="<%=cate_item.getCateID()%>"><%=cate_item.getCateName()%></option>
                                    <%}%>
                                </select>

                                <input name="searchProdID" placeholder="Search Products Here....." type="search">
                                <button class="btn" type="submit" name="submit" value="submit"><i class="ti-search"></i></button>
                            </div>
                        </div>
                    </form>
                </div>

                <div class="col-lg-2 col-md-3 col-12">
                    <div class="right-bar">
                        <!-- Search Form -->
                        <div class="sinlge-bar">
                            <a href="#" class="single-icon"><i class="fa fa-heart-o" aria-hidden="true"></i></a>
                        </div>
                        <div class="sinlge-bar">
                            <a href="#" class="single-icon"><i class="fa fa-user-circle-o" aria-hidden="true"></i></a>
                        </div>
                        <div class="sinlge-bar shopping">
                            <a href="#" class="single-icon"><i class="ti-bag"></i> <span class="total-count"><%=cart.size()%></span></a>
                            <!-- Shopping Item -->
                            <div class="shopping-item">
                                <div class="dropdown-cart-header">
                                    <span><%=cart.size()%> Items</span>
                                    <a href="cart">View Cart</a>
                                </div>
                                <ul class="shopping-list">
                                    <% while (cart_items.hasMoreElements()) {
                                            Product cart_item = cart_items.nextElement();
                                    %>
                                    <li>
                                        <a class="cart-img" href="#"><img src="asset/images/product/<%=cart_item.getImage()%>" alt="#"></a>
                                        <h4><a href="#"><%=cart_item.getPname()%></a></h4>
                                        <p class="quantity"><%=cart_item.getQuantity()%> - <span class="amount">$<%=cart_item.getPrice()%></span></p>
                                    </li>
                                    <%}%>
                                </ul>
                                <div class="bottom">
                                    <div class="total">
                                        <span>Total</span>
                                        <span class="total-amount">$<%=total%></span>
                                    </div>
                                    <a href="checkout" class="btn animate">Checkout</a>
                                </div>
                            </div>
                            <!--/ End Shopping Item -->
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Header Inner -->
    <div class="header-inner">
        <div class="container">
            <div class="cat-nav-head">
                <div class="row">

                    <% if (showCate == 1) {%>
                    <div class="col-lg-3">
                        <div class="all-category">
                            <h3 class="cat-heading"><i class="fa fa-bars" aria-hidden="true"></i>CATEGORIES</h3>
                            <ul class="main-category">
                                <%
                                    cate_items = Collections.enumeration(cate);
                                    while (cate_items.hasMoreElements()) {
                                        Category cate_item = cate_items.nextElement();
                                %>
                                <li><a href="category?cateID=<%=cate_item.getCateID()%>"><%=cate_item.getCateName()%></i></a>
                                        <%}%>
                            </ul>
                        </div>
                    </div>
                    <%} %>
                    <div class="col-lg-9 col-12">
                        <div class="menu-area">
                            <!-- Main Menu -->
                            <nav class="navbar navbar-expand-lg">
                                <div class="navbar-collapse">	
                                    <div class="nav-inner">	
                                        <ul class="nav main-menu menu navbar-nav">
                                            <li class="active"><a href=".">Home</a></li>
                                                <%
                                                    if (session.getAttribute("customer") != null) {
                                                %>
                                            <li><a href="#">Shop<i class="ti-angle-down"></i><span class="new">New</span></a>
                                                <ul class="dropdown">
                                                    <li><a href="cart">Cart</a></li>
                                                    <li><a href="checkout">Checkout</a></li>
                                                    <li><a href="bills">bills</a></li>
                                                </ul>
                                            </li>
                                            <li><a href="#">Customer Info</a></li>
                                            <li><a href="logout">Logout</a></li>
                                                <%} else { %>
                                            <li><a href="login">Login</a></li>
                                            <li><a href="admin">Admin</a></li>
                                                <% } %>
                                        </ul>
                                    </div>
                                </div>
                            </nav>
                            <!--/ End Main Menu -->	
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!--/ End Header Inner -->
    <!--/ End Header -->
    <%} %>

    <% if (privilege == 1) {
            Admin admin = (Admin) session.getAttribute("admin");
    %>
    <div class="topbar">
        <div class="container">
            <div class="row">
                <div class="col-lg-5 col-md-12 col-12">
                    <!-- Top Left -->
                    <div class="top-left">
                        <ul class="list-main">
                            <li><i class="ti-email"></i> chinhldse04423@fpt.edu.vn</li>
                        </ul>
                    </div>
                    <!--/ End Top Left -->
                </div>
                <div class="col-lg-7 col-md-12 col-12">
                    <!-- Top Right -->
                    <div class="right-content">
                        <ul class="list-main">
                            <% if (admin != null) {%>
                            <li><i class="ti-user"></i> <a href="account">Welcome, <%=admin.getUsername()%></a></li>
                            <li><i class="ti-power-off"></i><a href="logout">Logout</a></li>
                                    <%} else {%>
                            <li><i class="ti-power-off"></i><a href="login">Login</a></li>
                                    <%} %>
                        </ul>
                    </div>
                    <!-- End Top Right -->
                </div>
            </div>
        </div>
    </div>
    <div class="middle-inner">
        <div class="container">
            <div class="row">
                <div class="col-lg-2 col-md-2 col-12">
                    <!-- Logo -->
                    <div class="logo">
                        <a href="."><img src="asset/images/logo.png" alt="SE04423"></a>
                    </div>
                    <!--/ End Logo -->
                    <!-- Search Form -->
                    <!--/ End Search Form -->
                </div>
            </div>
        </div>
    </div>
    <div class="header-inner">
        <div class="container">
            <div class="cat-nav-head">
                <div class="row">
                    <div class="col-lg-9 col-12">
                        <div class="menu-area">
                            <!-- Main Menu -->
                            <nav class="navbar navbar-expand-lg">
                                <div class="navbar-collapse">	
                                    <div class="nav-inner">	
                                        <ul class="nav main-menu menu navbar-nav">
                                            <li class="active"><a href="admin">Home</a></li>
                                                <%
                                                    if (admin != null) {
                                                %>

                                            <li><a href="admin">Admin</a></li>
                                            <li><a href="MngCate">Categories</a></li>
                                            <li><a href="MngProd">Products</a></li>
                                            <li><a href="MngCust">Customers</a></li>
                                            <li><a href="MngBill">Bills</a></li>
                                            <li><a href="logout">Logout</a></li>
                                                <%} else { %>
                                            <li><a href="login">Login</a></li>
                                                <%}%>
                                        </ul>
                                    </div>
                                </div>
                            </nav>
                            <!--/ End Main Menu -->	
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <%}%>
</header>