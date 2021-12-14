<%-- 
    Document   : BillEdit
    Created on : Oct 16, 2021, 11:38:13 PM
    Author     : chinh
--%>

<%@page import="java.math.BigDecimal"%>
<%@page import="Entity.Bill"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.ResultSet"%>
<!DOCTYPE html>
<html>
    <%@include file="../nav/header.jsp" %>

    <body class="js">
        <%@include file="../nav/nav.jsp" %>

        <%
            Bill bill = (Bill) session.getAttribute("bill");

            String oID = (String) session.getAttribute("oID");

            String req = (String) request.getParameter("service");
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
                            <h3>Update progress bill <%=oID %></h3>
                        </div>
                        <div>
                            <% if (req.equalsIgnoreCase("updateInfo") && bill != null) {%>
                            <form action="MngBill?service=updateInfo" method="POST">
                                <table border="0">
                                    <tr>
                                        <td>Order ID</td>
                                        <td><input type="text"" name="oID" value="<%=bill.getoID()%>" readonly/></td>
                                    </tr>
                                    <tr>
                                        <td>Receiver's name</td>
                                        <td><input type="text" name="cname" value="<%=bill.getCname()%>" readonly/></td>
                                    </tr>
                                    <tr>
                                        <td>Phone</td>
                                        <td><input type="text" name="cphone" value="<%=bill.getCphone()%>" readonly/></td>
                                    </tr>
                                    <tr>
                                        <td>Address</td>
                                        <td><input type="text" name="cAddress" value="<%=bill.getcAddress()%>" readonly/></td>
                                    </tr>
                                    <tr>
                                        <td>Total</td>
                                        <td><input type="text" name="cAddress" value="$<%=BigDecimal.valueOf(bill.getTotal()).toPlainString() %>" readonly/></td>
                                    </tr>
                                    <% if (bill.getStatus() < 1) {%>
                                    <tr>
                                        <td>Status</td>
                                        <td>

                                            <select name="status" >
                                                <% if (bill.getStatus() < 0) {%>
                                                <option value="-1" <%=(bill.getStatus() == -1 ? "selected" : "")%> >Wait</option>
                                                <%}%>
                                                <option value="0" <%=(bill.getStatus() == -0 ? "selected" : "")%> >Processing</option>
                                                <option value="1" <%=(bill.getStatus() == 1 ? "selected" : "")%> >Done</option>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2">
                                            <input type="submit" value="Update Info" name="submit" class="btn"/>
                                            <input type="reset" name="Reset" class="btn"/>
                                        </td>
                                    </tr>
                                    <%} else { %>
                                    <tr>
                                    <td>Status</td>
                                    <td>
                                        Done
                                    </td>
                                    </tr>
                                    <%} %>
                                </table>
                            </form>
                            <%}
           %>
                        </div>
                    </div>

                </div>
            </div>
        </div>


        <%@include file="../nav/footer.jsp" %>
    </body>
</html>
