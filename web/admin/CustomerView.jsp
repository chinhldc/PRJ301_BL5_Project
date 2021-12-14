<%-- 
    Document   : CustomerView
    Created on : Oct 16, 2021, 3:44:15 AM
    Author     : chinh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.ResultSet"%>
<!DOCTYPE html>
<html>
    <%@include file="../nav/header.jsp" %>

    <body class="js">
        <%@include file="../nav/nav.jsp" %>

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
                        <div class="btn">
                            <a href="MngCust?service=insert">Add Customer</a>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <br><br>
                </div>
                <div class="row">
                    <div class="col-lg-12 col-12">
                        <div>
                            <%
                                ArrayList<Customer> cust_list = (ArrayList) session.getAttribute("cust_list");
                                Enumeration<Customer> customers = Collections.enumeration(cust_list);
                            %>
                            <table class="table shopping-summery">
                                <thead>
                                    <tr class="main-hading">
                                        <th class="text-center">CID</th>
                                        <th class="text-center">CNAME</th>
                                        <th class="text-center">CPHONE</th>
                                        <th class="text-center">CADDRESS</th>
                                        <th class="text-center">USERNAME</th>
                                        <th class="text-center">PASSWORD</th>
                                        <th class="text-center">STATUS</th>
                                        <th class="text-center" colspan="3">ACTION</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        while (customers.hasMoreElements()) {
                                            Customer customer = customers.nextElement();
                                    %>
                                    <tr>
                                        <td><%=customer.getCid() %></td>
                                        <td><%=customer.getCname() %></td>
                                        <td><%=customer.getCphone() %></td>
                                        <td><%=customer.getcAddress() %></td>
                                        <td><%=customer.getUsername() %></td>
                                        <td><%=customer.getPassword() %></td>
                                        <td><%=(customer.getStatus() == 1 ? "Enable" : "Disable")%></td>
                                        <td><a href="MngCust?service=changePswd&cid=<%=customer.getCid() %>">Change password</a></td>
                                        <td><a href="MngCust?service=update&cid=<%=customer.getCid() %>">Update</a></td>
                                        <td><a href="MngCust?service=delete&cid=<%=customer.getCid() %>">Delete</a></td>
                                    </tr>
                                    <%
                                        }
                                    %>
                                </tbody>
                            </table>
                        </div>
                    </div>

                </div>
            </div>
        </div>




        <%@include file="../nav/footer.jsp" %>
    </body>
</html>
