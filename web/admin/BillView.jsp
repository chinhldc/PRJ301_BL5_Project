<%-- 
    Document   : BillView
    Created on : Oct 16, 2021, 3:43:43 AM
    Author     : chinh
--%>

<%@page import="Entity.Bill"%>
<%@page import="java.math.BigDecimal"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.ResultSet"%>
<!DOCTYPE html>
<html>
    <%@include file="../nav/header.jsp" %>
    <body class="js">
        <%@include file="../nav/nav.jsp" %>


        <%
            ArrayList<Bill> bills = (ArrayList) session.getAttribute("bills");
            Enumeration<Bill> bills_items = Collections.enumeration(bills);
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

                    </div>
                </div>
                <div class="row">
                    <br><br>
                    <table class="table shopping-summery">
                        <thead>
                            <tr>
                                <td>oID</td>
                                <th>dateCreate</th>
                                <th>cname</th>
                                <th>cphone</th>
                                <th>cAddress</th>
                                <th>total</th>
                                <th>status</th>
                                <th>cid</th>
                                <th>Detail</th>
                                <th>Update info</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% while (bills_items.hasMoreElements()) {
                            Bill bill = bills_items.nextElement();
                            %>
                            <tr>
                                <td><%=bill.getoID() %></td>
                                <td><%=bill.getDateCreate() %></td>
                                <td><%=bill.getCname() %></td>
                                <td><%=bill.getCphone() %></td>
                                <td><%=bill.getcAddress() %></td>
                                <td>$<%=BigDecimal.valueOf(bill.getTotal()).toPlainString() %></td>
                                <td><%= (bill.getStatus() == 1 ? "Done" : (bill.getStatus() == 0 ? "Processing" : "<b>New</b>"))%></td>
                                <td><%=bill.getCid() %></td>
                                <td><a href="MngDetail?bill=<%=bill.getoID() %>">Detail</a></td>
                                <td><a href="MngBill?service=updateInfo&oID=<%=bill.getoID() %>">Update Status</a></td>
                            </tr>
                            <%}
                           %>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <%@include file="../nav/footer.jsp" %>
    </body>
</html>
