<%-- 
    Document   : CustomerEdit
    Created on : Oct 16, 2021, 3:44:25 AM
    Author     : chinh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="Entity.Customer"%>
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
                        <div>

                        </div>
                    </div>
                </div>
                <div class="row">
                    <br><br>
                </div>
                <div class="row">
                    <div class="col-lg-12 col-12">
                        
                    </div>
                    <div class="btn"><p><a href="MngCust?service=insert">Add Customer</a></p></div>
                </div>
            </div>
        </div>

        <%
            String req = (String) request.getParameter("service");
            ResultSet rs = (ResultSet) request.getAttribute("rs");
            String title = (String) request.getAttribute("title");

            Customer cus = new Customer(0, "", "", "", "", "", 1);

            if (req.equalsIgnoreCase("update") && rs.next()) {
                cus.setCid(rs.getInt(1));
                cus.setCname(rs.getString(2));
                cus.setCphone(rs.getString(3));
                cus.setcAddress(rs.getString(4));
                cus.setUsername(rs.getString(5));
                cus.setPassword(rs.getString(6));
                cus.setStatus(rs.getInt(7));
            }
        %>

        <%  if (req.equals("insert") || req.equals("update")) {%>
        <form action="MngCust?service=<%=(req.endsWith("insert") ? "insert" : "update")%>" method="POST">
            <table border="0">
                <tr hidden>
                    <td><input type="text" name="cid" value="<%= cus.getCid()%>"</td>
                </tr>
                <tr>
                    <td>Name:</td>
                    <td><input type="text" name="cname" value="<%= cus.getCname()%>" /></td>
                </tr>
                <tr>
                    <td>Phone:</td>
                    <td><input type="text" name="cphone" value="<%= cus.getCphone()%>" /></td>
                </tr>
                <tr>
                    <td>Address:</td>
                    <td><input type="text" name="cAddress" value="<%= cus.getcAddress()%>" /></td>
                </tr>
                <tr>
                    <td>Username:</td>
                    <td><input type="text" name="username" value="<%= cus.getUsername()%>" /></td>
                </tr>
                <tr>
                    <td>Password:</td>
                    <td><input type="text" name="password" value="<%= cus.getPassword()%>" /></td>
                </tr>
                <% if (req.equalsIgnoreCase("update")) {%>
                <tr>
                    <td>Status:</td>
                    <td>
                        <input type="radio" name="status" value="1" <%=(cus.getStatus() == 1 ? "checked" : "")%>/>Enable
                        <input type="radio" name="status" value="0" <%=(cus.getStatus() == 0 ? "checked" : "")%>/>Disable
                    </td>
                </tr>
                <% }%>
                <tr>
                    <td>
                        <input type="submit" value="<%=(req.endsWith("insert") ? "Register" : "Update")%>" name="submit"/>
                        <input type="reset" value="Reset"/>
                    </td>
                </tr>
            </table>
        </form>
        <% }%>

        <% if (req.equals("changePswd") && rs.next()) {%>
        <form action="MngCust?service=changePswd" method="POST">
            <table border ="0">
                <caption><%=title%></caption>
                <tr hidden>
                    <td><input type="text" name="cid" value="<%=rs.getInt(1)%>" readonly /></td>
                </tr>
                <tr>
                    <td>Username</td>
                    <td><input type="text" name="username" value="<%=rs.getString(2)%>" readonly/></td>
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
                        <input type="submit" value="Update" name="submit"/>
                        <input type="reset" value="Reset" />
                    </td>
                </tr>
            </table>
        </form>
        <% }%>
        <%@include file="../nav/footer.jsp" %>
    </body>
</html>
