<%-- 
    Document   : DetailView
    Created on : Oct 17, 2021, 1:07:10 AM
    Author     : chinh
--%>

<%@page import="java.util.Collections"%>
<%@page import="java.util.Enumeration"%>
<%@page import="Entity.BillDetail"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.ResultSet"%>
<!DOCTYPE html>
<html>
    <%@include file="../nav/header.jsp" %>
    <body class="js">
        <%@include file="../nav/nav.jsp" %>

        <%
            ArrayList<BillDetail> details = (ArrayList) session.getAttribute("details");
            Enumeration<BillDetail> details_items = Collections.enumeration(details);
            String oID = (String) session.getAttribute("oID");
        %>

        <div class="shopping-cart section">
            <div class="container">
                <div class="row">
                    <div class="col-lg-4 col-12">
                        <div class="order-details">
                            <div class="single-widget">
                                <h2>ADMIN MENU</h2>
                                <div class="content">
                                    <ul>
                                        <li class="last"><a href="admin">List Admin</a></li>
                                        <li class="last"><a href="MngCust">List Customer</a></li>
                                        <li class="last"><a href="MngCate">List Category</a></li>
                                        <li class="last"><a href="MngProd">List Product</a></li>
                                        <li class="last"><a href="MngBill">List Bill</a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-8 col-12">
                        <div>
                            <h3>Detail bill <%=oID%></h3>
                        </div>
                        <div>
                            <table class="table shopping-summery">
                                <thead>
                                    <tr class="main-hading">
                                        <th class="text-center">pid</th>
                                        <th class="text-center">quantity</th>
                                        <th class="text-center">price</th>
                                        <th class="text-center">total</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <% double details_total = 0; %>
                                    <% while (details_items.hasMoreElements()) {

                                            BillDetail detail = details_items.nextElement();
                                            details_total += detail.getPrice() * detail.getQuantity();
                                    %>
                                    <tr class="text-center">
                                        <td><%=detail.getPid()%></td>
                                        <td><%=detail.getQuantity()%></td>
                                        <td>$<%=detail.getPrice()%></td>
                                        <td>$<%=detail.getTotal()%></td>
                                    </tr>
                                    <%}%>
                                    <tr class="text-center">
                                        <td colspan="3">Total</td>
                                        <td colspan="1"><%=details_total%></td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                        <div class='btn'>
                            <a href='MngBill?service=updateInfo&oID=<%=oID%>'>Change status</a>
                        </div>
                    </div>

                </div>
            </div>
        </div>

        <%@include file="../nav/footer.jsp" %>
    </body>
</html>
