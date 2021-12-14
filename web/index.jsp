<%-- 
    Document   : index
    Created on : Oct 28, 2021, 2:15:09 PM
    Author     : Chinh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@include file="nav/header.jsp" %>
    
    <body class="js">
        <%@include file="nav/nav.jsp" %>

        <%@page import="java.util.Enumeration"%>
        <%@page import="java.util.Collections"%>
        <%@page import="java.util.ArrayList"%>
        <%@page import="Entity.Product"%>
        <%
            ArrayList<Product> shop_list = (ArrayList) session.getAttribute("shop_list");

            Enumeration shop_enm = Collections.enumeration(shop_list);
        %>


        <!-- Start Product Area -->
        <div class="product-area section">
            <div class="container">
                <div class="row">
                    <div class="col-12">
                        <div class="section-title">
                            <h2>Shopping</h2>
                            <%
                            String searchMessage = (String) session.getAttribute("searchMessage");
                            if (searchMessage != null || !searchMessage.isEmpty()) {
                            %>
                            <h3><%=searchMessage %></h3>
                            <%}%>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-12">
                        <div class="product-info">
                            <div class="tab-content" id="myTabContent">
                                <!-- Start Single Tab -->
                                <div class="tab-pane fade show active" role="tabpanel">
                                    <div class="tab-single">
                                        <div class="row">
                                            <%
                                                while (shop_enm.hasMoreElements()) {
                                                    Product item = (Product) shop_enm.nextElement();
                                            %>
                                            <div class="col-xl-3 col-lg-4 col-md-4 col-12">
                                                <div class="single-product">
                                                    <div class="product-img">
                                                        <a href="product?pid=<%=item.getPid() %>" style="width: 262.5px; height: 175px;">
                                                            <img class="default-img" src="asset/images/product/<%=item.getImage()%>" alt="#" style="width: auto; height: auto;">
                                                            <img class="hover-img" src="asset/images/product/<%=item.getImage()%>" alt="#" style="width: auto; height: auto;">
                                                        </a>
                                                        <div class="button-head">
                                                            <div class="product-action">
                                                                <a data-toggle="modal" data-target="#exampleModal" title="Quick View" href="product?pid=<%=item.getPid() %>"><i class=" ti-eye"></i><span>Quick Shop</span></a>
                                                                <a title="Wishlist" href="#"><i class=" ti-heart "></i><span>Add to Wishlist</span></a>
                                                            </div>
                                                            <div class="product-action-2">
                                                                <a title="Add to cart" href="addtocart?pid=<%=item.getPid() %>&quantity=1">Add to cart</a>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="product-content">
                                                        <h3><a href="product-details.html"><%=item.getPname()%></a></h3>
                                                        <div class="product-price">
                                                            <span>$<%=item.getPrice()%></span>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <%
                                                }
                                            %>
                                        </div>
                                    </div>
                                </div>
                                <!--/ End Single Tab -->
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- End Product Area -->

        <%@include file="nav/footer.jsp" %>
    </body>
</html>
