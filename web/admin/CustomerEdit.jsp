<%-- 
    Document   : CustomerEdit
    Created on : Oct 16, 2021, 3:44:25 AM
    Author     : chinh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                            <%
                            String req = (String) request.getParameter("service");
                            String title = (String) request.getAttribute("title");
                        %>
                        <c:set var="customer" value="${requestScope.customer}" scope="request"></c:set>
                        <%  if (req.equals("insert") || req.equals("update")) {%>
                        <form action="" method="POST">
                            <table border="0">
                                <tr hidden>
                                    <td><input type="text" name="cid" value="${customer.cid != null ? customer.cid : 0}">
                                    </td>
                                </tr>
                                <tr>
                                    <td>Name:</td>
                                    <td><input type="text" name="cname" value="${customer.cname != null ? customer.cname : ""}" /></td>
                                </tr>
                                <tr>
                                    <td>Phone:</td>
                                    <td><input type="text" name="cphone" value="${customer.cphone != null ? customer.cphone : ""}" /></td>
                                </tr>
                                <tr>
                                    <td>Address:</td>
                                    <td><input type="text" name="cAddress" value="${customer.cAddress != null ? customer.cAddress : ""}" /></td>
                                </tr>
                                <tr>
                                    <td>Username:</td>
                                    <td><input type="text" name="username" value="${customer.username != null ? customer.username : ""}" /></td>
                                </tr>
                                <% if (req.equalsIgnoreCase("insert")) {%>
                                <tr>
                                    <td>Password:</td>
                                    <td><input type="text" name="password" value="" /></td>
                                </tr>
                                <%}%>
                                <% if (req.equalsIgnoreCase("update")) {%>
                                <tr>
                                    <td>Status:</td>
                                    <td>
                                        <input type="radio" name="status" value="1" <c:if test = "${customer.status == 1}">checked</c:if>/>Enable
                                        <input type="radio" name="status" value="0" <c:if test = "${customer.status == 0}">checked</c:if>/>Disable
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

                        <% if (req.equals("changePswd")) {%>
                        <form action="MngCust?service=changePswd" method="POST">
                            <table border ="0">
                                <tr hidden>
                                    <td><input type="text" name="cid" value="${requestScope.customer.cid}" readonly /></td>
                                </tr>
                                <tr>
                                    <td>Username</td>
                                    <td><input type="text" name="username" value="${requestScope.customer.cname}" readonly/></td>
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
                        </div>
                    </div>
                </div>
                <div class="row">
                    <br><br>
                </div>
                <div class="row">
                    <div class="col-lg-12 col-12">
                        
                    </div>
                </div>
            </div>
        </div>
        <%@include file="../nav/footer.jsp" %>
    </body>
</html>
