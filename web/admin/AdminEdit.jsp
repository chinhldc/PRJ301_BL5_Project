<%-- 
    Document   : AdminEdit
    Created on : Oct 16, 2021, 3:43:27 AM
    Author     : chinh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.ResultSet"%>
<!DOCTYPE html>
<html>
    <%@include file="../nav/header.jsp" %>

    <body class="js">
        <%@include file="../nav/nav.jsp" %>

        <%
            Admin adm = (Admin) session.getAttribute("adm");
            String req = (String) request.getParameter("service");
            String title = (String) session.getAttribute("title");
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
                        <div class="form-main">
                            <% if (req.equals("insert")) {%>
                            <div class="title">
                                <h3>Add new admin</h3>
                            </div>
                            <form action="admin?service=insert" method="POST">
                                <table border ="0">
                                    <tr>
                                        <td>Username*</td>
                                        <td><input type="text" name="username" value="" required></td>
                                    </tr>
                                    <tr>
                                        <td>Password*</td>
                                        <td><input type="text" name="password" value="" required></td>
                                    </tr>
                                    <tr>
                                        <td colspan="2">
                                            <input type="submit" name="submit" class="btn" value="Add"/>
                                            <input type="reset" class="btn" value="Reset" />
                                        </td>
                                    </tr>
                                </table>
                            </form>

                            <% }%>

                            <% if (req.equals("changePswd") && adm != null) {%>
                            <h3><%=title%></h3>
                            <form action="admin?service=changePswd" method="POST">
                                <table border ="0">
                                    <tr hidden>
                                        <td><input type="text" name="adminID" value="<%=adm.getAdminID() %>" readonly /></td>
                                    </tr>
                                    <tr>
                                        <td>Username</td>
                                        <td><input type="text" name="username" value="<%=adm.getUsername() %>" readonly/></td>
                                    </tr>
                                    <tr>
                                        <td>Old password</td>
                                        <td><input type="password" name="oldPW" value="" /></td>
                                    </tr>
                                    <tr>
                                        <td>New password</td>
                                        <td><input type="password" name="newPW" value="" /></td>
                                    </tr>
                                    <tr>
                                        <td>Confirmation</td>
                                        <td><input type="password" name="checkPW" value="" /></td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <input type="submit" value="Update" name="submit" class="btn"/>
                                            <input type="reset" value="Reset" class="btn"/>
                                        </td>
                                    </tr>
                                </table>
                            </form>
                            <% }%>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <%@include file="../nav/footer.jsp" %>
    </body>
</html>
