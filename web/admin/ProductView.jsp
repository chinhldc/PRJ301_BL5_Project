<%-- 
    Document   : ViewProduct
    Created on : Oct 5, 2021, 3:05:31 PM
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
            ResultSet rs = (ResultSet) request.getAttribute("result");
            String title = (String) request.getAttribute("title");
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
                            <a href="MngProd?service=insert">Add Product</a>
                        </div>
                        <div>
                            <form action="MngProd?service=search" method="POST">
                                <table border="0">
                                    <tr>
                                        <td>Search by name: </td>
                                        <td><input type="text" name="search" value="" /></td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                        <td>
                                            <input type="submit" name="submit" value="Search"/>
                                            <input type="reset" name="reset" value="Reset" />
                                        </td>
                                    </tr>
                                </table>
                            </form>
                            <form id="sortForm" action="MngProd" method="POST">
                                <table border="0">
                                    <tr>
                                        <td>Sort by Price</td>
                                        <td>
                                            <input type="radio" name="sort" value="1" onClick="submitForm()"/>Ascending
                                            <input type="radio" name="sort" value="0" onClick="submitForm()"/>Descending
                                        </td>
                                    </tr>
                                </table>
                            </form>
                            <script>
                                function submitForm() {
                                    document.getElementById("sortForm").submit();
                                }
                            </script>
                        </div>

                    </div>
                </div>
                <div class="row">
                    <br><br>
                </div>
                <div class="row">
                    <div>
                        <table class="table shopping-summery">
                            <caption><%=title%></caption>
                            <thead>
                                <tr>
                                    <th>PID</th>
                                    <th>Product Name</th>
                                    <th>Image</th>
                                    <th>Quantity</th>
                                    <th>Price</th>
                                    <th>Description</th>
                                    <th>Status</th>
                                    <th>CateID</th>
                                    <th>Update</th>
                                    <th>Delete</th>
                                </tr>
                            </thead>
                            <tbody>
                                <% while (rs.next()) {%>
                                <tr>
                                    <td><%=rs.getString(1)%></td>
                                    <td><%=rs.getString(2)%></td>
                                    <td><%=rs.getString(5)%></td>
                                    <td><%=rs.getInt(3)%></td>
                                    <td><%=rs.getDouble(4)%></td>
                                    <td><%=rs.getString(6)%></td>
                                    <td><%=(rs.getInt(7) == 1 ? "Enable" : "Disable")%></td>
                                    <td><%=rs.getString(9)%></td>
                                    <td><a href="MngProd?service=update&pid=<%=rs.getString(1)%>">Update</a></td>
                                    <td><a href="MngProd?service=delete&pid=<%=rs.getString(1)%>">Delete</a></td>
                                </tr>
                                <%}%>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="../nav/footer.jsp" %>
    </body>
</html>
