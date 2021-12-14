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
        
        <p><a href=".">Index page</a></p>
        <p>List Product</p>
        <p><a href="MngProd?service=insert">Add Product</a></p>
        <div>
            <form action="MngProd?service=search" method="POST">
                <table border="0">
                    <tr>
                        <td>Search by name: </td>
                        <td><input type="text" name="search" value="" /></td>
                    </tr>
                    <tr>
                        <td><input type="submit" name="submit" value="Search" /></td>
                        <td><input type="reset" name="reset" value="Reset" /></td>
                    </tr>
                </table>
            </form>
            <form id="sortForm" action="product" method="POST">
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

        
        <table border="1">
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
                <% while (rs.next()) { %>
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
            <%@include file="../nav/footer.jsp" %>
    </body>
</html>
