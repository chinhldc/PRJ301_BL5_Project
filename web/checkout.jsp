<%-- 
    Document   : checkout
    Created on : Nov 1, 2021, 1:15:06 PM
    Author     : chinh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@include file="nav/header.jsp" %>

    <body class="js">
        <%@include file="nav/nav.jsp" %>

        <%
            Customer customer = (Customer) session.getAttribute("customer");

            ArrayList<Product> cart = (ArrayList) session.getAttribute("cart");

            Enumeration<Product> cart_items = Collections.enumeration(cart);
        %>

        <!-- Breadcrumbs -->
        <div class="breadcrumbs">
            <div class="container">
                <div class="row">
                    <div class="col-12">
                        <div class="bread-inner">
                            <ul class="bread-list">
                                <li><a href="index1.html">Home<i class="ti-arrow-right"></i></a></li>
                                <li class="active"><a href="checkout">Checkout</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- End Breadcrumbs -->

        <!-- Start Checkout -->
        <section class="shop checkout section">
            <div class="container">
                <% if (cart.isEmpty()) { %>
                <div class="row">
                    <div class="col-lg-4 col-12">
                    </div>
                    <div class="col-lg-4 col-12">
                        <h3>Your cart is empty. Please back to shopping.</h3>
                    </div>
                    <div class="col-lg-4 col-12">
                    </div>
                </div>
                <%} else {%>
                <form class="form" method="post" action="">
                    <div class="row"> 
                        <div class="col-lg-8 col-12">
                            <div class="checkout-form">
                                <h2>Confirm shipping info</h2>
                                <!-- Form -->

                                <div class="row">
                                    <div class="col-lg-12 col-md-12 col-12">
                                        <div class="form-group">
                                            <label>Name<span>*</span></label>
                                            <input type="text" name="name" placeholder="" required="required" value="<%=customer.getCname()%>">
                                        </div>
                                    </div>
                                    <div class="col-lg-6 col-md-6 col-12">
                                        <div class="form-group">
                                            <label>Phone Number<span>*</span></label>
                                            <input type="text" name="phone" placeholder="" required="required" value="<%=customer.getCphone()%>">
                                        </div>
                                    </div>
                                    <div class="col-lg-6 col-md-6 col-12">
                                        <div class="form-group">
                                            <label>Address<span>*</span></label>
                                            <input type="text" name="address" placeholder="" required="required" value="<%=customer.getcAddress()%>">
                                        </div>
                                    </div>
                                </div>
                                </form>
                                <!--/ End Form -->
                            </div>
                        </div>
                        <div class="col-lg-4 col-12">
                            <div class="order-details">
                                <!-- Order Widget -->
                                <div class="single-widget">
                                    <h2>CART  TOTALS</h2>
                                    <div class="content">
                                        <ul>
                                            <%
                                                cart_items = Collections.enumeration(cart);
                                                total = 0;
                                                while (cart_items.hasMoreElements()) {
                                                    Product prod = cart_items.nextElement();
                                                    total += prod.getPrice() * prod.getQuantity();

                                            %>
                                            <li><%=prod.getPname()%> x <%=prod.getQuantity()%> <span><%=prod.getPrice() * prod.getQuantity()%></span></li>
                                                <%}%>
                                            <li class="last">Total<span>$<%=total%></span></li>
                                        </ul>
                                    </div>
                                </div>
                                <!--/ End Order Widget -->
                                <!-- Button Widget -->
                                <div class="single-widget get-button">
                                    <div class="content">
                                        <div class="button">
                                            <input type="submit" name="submit" class="btn" value="Proceed to checkout">
                                        </div>
                                    </div>
                                </div>
                                <!--/ End Button Widget -->
                            </div>
                        </div>
                    </div>
                </form>
                <%} %>
            </div>
        </section>
        <!--/ End Checkout -->

        <%@include file="nav/footer.jsp" %>
    </body>
</html>
