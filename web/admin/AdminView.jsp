<%-- 
    Document   : AdminView
    Created on : Oct 16, 2021, 3:43:16 AM
    Author     : chinh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
                                    <c:forEach items="${sessionScope.admin_list}" var="current">
                                        <tr class="text-center">
                                        <td>${current.adminID}</td>
                                        <td>${current.username}</td>
                                        <td>${current.password}</td>
                                        <td><a href="admin?service=changePswd&adminID=${current.adminID}">Change password</a></td>
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
