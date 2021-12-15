<%-- 
    Document   : CustomerView
    Created on : Oct 16, 2021, 3:44:15 AM
    Author     : chinh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                                    <c:forEach items="${sessionScope.cust_list}" var="current">
                                        <tr>
                                            <td>${current.cid}</td>
                                            <td>${current.cname}</td>
                                            <td>${current.cphone}</td>
                                            <td>${current.cAddress}</td>
                                            <td>${current.username}</td>
                                            <td>${current.password}</td>
                                            <td>
                                                <c:choose>
                                                    <c:when test="${current.status == 1}">
                                                        Enable
                                                    </c:when>
                                                    <c:otherwise>
                                                        Disable
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>
                                            <td><a href="MngCust?service=changePswd&cid=${current.cid}">Change password</a></td>
                                            <td><a href="MngCust?service=update&cid=${current.cid}">Update</a></td>
                                            <td><a href="MngCust?service=delete&cid=${current.cid}">Delete</a></td>
                                        </tr>
                                    </c:forEach>
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
