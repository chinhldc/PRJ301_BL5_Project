<%-- 
    Document   : AdminView
    Created on : Oct 16, 2021, 3:43:16 AM
    Author     : chinh
--%>

<%@page import="java.util.Enumeration"%>
<%@page import="Entity.Admin"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Collections"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.ResultSet"%>
<!DOCTYPE html>
<html>
    <%@include file="../nav/header.jsp" %>

    <body class="js">
        <%@include file="../nav/nav.jsp" %>

        <%
            ArrayList<Admin> admin_list = (ArrayList) session.getAttribute("admin_list");

            Enumeration<Admin> admlist = Collections.enumeration(admin_list);
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
                        <div class="btn">
                            <a href="admin?service=insert">Add new</a>
                        </div>
                        <br><br>
                        <div>
                            <table class="table shopping-summery">
                                <thead>
                                    <tr class="main-hading">
                                        <th class="text-center">ADMIN ID</th>
                                        <th class="text-center">USERNAME</th>
                                        <th class="text-center">PASSWORD</th>
                                        <th class="text-center">ACTION</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <% while (admlist.hasMoreElements()) {
                                            Admin admin = admlist.nextElement();
                                    %>
                                    <tr class="text-center">
                                        <td><%=admin.getAdminID()%></td>
                                        <td><%=admin.getUsername()%></td>
                                        <td><%=admin.getPassword()%></td>
                                        <td><a href="admin?service=changePswd&adminID=<%=admin.getAdminID()%>">Change password</a></td>
                                    </tr>
                                    <%}
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
