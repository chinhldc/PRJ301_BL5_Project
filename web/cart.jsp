<%-- 
    Document   : cart
    Created on : Nov 2, 2021, 10:46:39 AM
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
                                <li><a href=".">Home<i class="ti-arrow-right"></i></a></li>
                                <li class="active"><a href="cart">Cart</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- End Breadcrumbs -->

        <!-- Shopping Cart -->
        <div class="shopping-cart section">
            <div class="container">
                <form action="cart" method="POST">
                    <div class="row">
                        <div class="col-12">
                            <!-- Start Cart -->
                            <table class="table shopping-summery">
                                <thead>
                                    <tr class="main-hading">
                                        <th>PRODUCT</th>
                                        <th>NAME</th>
                                        <th class="text-center">UNIT PRICE</th>
                                        <th class="text-center">QUANTITY</th>
                                        <th class="text-center">TOTAL</th> 
                                        <th class="text-center"><i class="ti-trash remove-icon"></i></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        cart_items = Collections.enumeration(cart);
                                        while (cart_items.hasMoreElements()) {
                                            Product cart_item = (Product) cart_items.nextElement();
                                    %>
                                    <tr>
                                        <td class="image" ><img src="asset/images/product/<%=cart_item.getImage()%>" alt="#"></td>
                                        <td class="product-des">
                                            <p class="product-name"><a href="product?pid=<%=cart_item.getPid()%>"><%=cart_item.getPname()%></a></p>
                                            <p class="product-des"><%=cart_item.getDescription()%></p>
                                        </td>
                                        <td class="price"><span>$<%=cart_item.getPrice()%> </span></td>
                                        <td class="qty"><!-- Input Order -->
                                            <div class="input-group" style="text-align:center; margin-left: 50px;">
                                                <input type="number" name="<%=cart_item.getPid()%>"  min="1" value="<%=cart_item.getQuantity()%>" style="width: 5em; text-align: center;">
                                                <%
                                                String noti = (String) session.getAttribute((String) "noti" + cart_item.getPid());
                                                %>
                                                <%
                                                if (noti != null) { %>
                                                <%=noti %>
                                                <%}%>
                                            </div>
                                            <!--/ End Input Order -->
                                        </td>
                                        <td class="total-amount"><span>$<%=cart_item.getPrice() * cart_item.getQuantity()%></span></td>
                                        <td class="action"><a href="cart?delete=<%=cart_item.getPid()%>"><i class="ti-trash remove-icon"></i></a></td>
                                    </tr>
                                    <%}%>
                                </tbody>
                            </table>

                            <!--/ End Cart -->
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-12">
                            <!-- Total Amount -->
                            <div class="total-amount">
                                <div class="row">
                                    <div class="col-lg-8 col-md-5 col-12">
                                        <div class="left">
                                            <div class="coupon">

                                                <input type="submit" name="update" class="btn" value="Update Quantity">
                                                <input type="submit" name="delete" class="btn" value="Delete Cart">

                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-lg-4 col-md-7 col-12">
                                        <div class="right">
                                            <ul>
                                                <li>Cart Subtotal<span>$<%=total%></span></li>
                                                <li class="last">You Pay<span>$<%=total%></span></li>
                                            </ul>
                                            <div class="button5">
                                                <a href="checkout" class="btn">Checkout</a>
                                                <a href="." class="btn">Continue shopping</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!--/ End Total Amount -->
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <!--/ End Shopping Cart -->

        <%@include file="nav/footer.jsp" %>
    </body>
</html>
