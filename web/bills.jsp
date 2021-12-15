<%-- 
    Document   : bills
    Created on : Nov 7, 2021, 2:15:23 AM
    Author     : chinh
--%>

<%@page import="Entity.Bill"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
    <%@include file="nav/header.jsp" %>
    <body class="js">
        <%@include file="nav/nav.jsp" %>
        <!-- Breadcrumbs -->
        <div class="breadcrumbs">
            <div class="container">
                <div class="row">
                    <div class="col-12">
                        <div class="bread-inner">
                            <ul class="bread-list">
                                <li><a href=".">Home<i class="ti-arrow-right"></i></a></li>
                                <li class="active"><a href="bills">Bills</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- End Breadcrumbs -->
        <!-- Bills List -->
        <div class="shopping-cart section">
            <div class="container">
                <div class="row">
                    <div class="col-12">
                        <!-- Start Cart -->
                        <table class="table shopping-summery">
                            <thead>
                                <tr class="main-hading">
                                    <th>ORDER ID</th>
                                    <th>DATE</th>
                                    <th class="text-center">RECEIVER</th>
                                    <th class="text-center">PHONE</th>
                                    <th class="text-center">ADDRESS</th>
                                    <th class="text-center">TOTAL</th> 
                                    <th class="text-center">STATUS</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${sessionScope.bills}" var="current">
                                <tr>
                                    <td class="image" >${current.oID}</td>
                                    <td class="image" >${current.dateCreate}</td>
                                    <td class="image" >${current.cname}</td>
                                    <td class="image" >${current.cphone}</td>
                                    <td class="image" >${current.cAddress}</td>
                                    <td class="image" >$${current.total}</td>
                                    <td class="image" >
                                        <c:choose>
                                            <c:when test="${current.status == 0}">
                                                Processing
                                            </c:when>
                                            <c:when test="${current.status == 1}">
                                                Delivered
                                            </c:when>
                                        </c:choose>
                                    </td>
                                </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                        <!--/ End Cart -->
                    </div>
                </div>
            </div>
        </div>
        <!--/ End Bills List -->
    </body>
</html>
