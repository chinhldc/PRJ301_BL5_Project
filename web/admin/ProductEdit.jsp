<%-- 
    Document   : EditProduct
    Created on : Oct 8, 2021, 9:59:40 AM
    Author     : chinh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="Entity.Product"%>
<!DOCTYPE html>
<html>
    <%@include file="../nav/header.jsp" %>

    <body class="js">
        <%@include file="../nav/nav.jsp" %>
        <p><a href=".">Index page</a><p>
        <p><a href="MngProd">List Product</a></p>

        <%
            String req = (String) request.getParameter("service");
            ResultSet rs = (ResultSet) request.getAttribute("rs");
            ResultSet rsCate = (ResultSet) request.getAttribute("rsCate");
            String title = (String) request.getAttribute("title");
            Product pro = new Product("", "", 0, 0, "", "", 1, 1);
            if (rs.next()) {
                pro.setPid(rs.getString(1));
                pro.setPname(rs.getString(2));
                pro.setQuantity(rs.getInt(3));
                pro.setPrice(rs.getDouble(4));
                pro.setImage(rs.getString(5));
                pro.setDescription(rs.getString(6));
                pro.setStatus(rs.getInt(7));
                pro.setCateID(rs.getInt(8));
            }
        %>

        <%
            if (req.equals("insert")) {
        %>
        <form action="product?service=insert" method="POST">
            <% }%>

            <%
                if (req.equals("update")) {
            %>
            <form action="MngProd?service=update" method="POST">
                <% }%>
                <table border="0">
                    <caption><%=title%></caption>
                    <tr>
                        <td>Product ID:</td>
                        <td><input type="text" name="id" value="<%= pro.getPid()%>" <%=(req.equals("update") ? "readonly" : "")%> /></td>
                    </tr>
                    <tr>
                        <td>Product Name:</td>
                        <td><input type="text" name="pname" value="<%= pro.getPname()%>" /></td>
                    </tr>
                    <tr>
                        <td>Quantity:</td>
                        <td><input type="text" name="quantity" value="<%=pro.getQuantity()%>" /></td>
                    </tr>
                    <tr>
                        <td>Price:</td>
                        <td><input type="text" name="price" value="<%= pro.getPrice()%>" /></td>
                    </tr>
                    <tr>
                        <td>Image Name:</td>
                        <td><input type="text" name="image" value="<%= pro.getImage()%>" /></td>
                    </tr>
                    <tr>
                        <td>Description:</td>
                        <td><input type="text" name="des" value="<%= pro.getDescription()%>" /></td>
                    </tr>
                    <%
                        if (req.equals("update")) {
                    %>
                    <tr>
                        <td>Status:</td>
                        <td>
                            <input type="radio" name="status" value="1" <%=(pro.getStatus() == 1 ? "checked" : "")%>/>Enable
                            <input type="radio" name="status" value="0" <%=(pro.getStatus() == 0 ? "checked" : "")%>/>Disable
                        </td>
                    </tr>
                    <% }%>
                    <tr>
                        <td>Category:</td>
                        <td>
                            <select name="cate" size="1">
                                <% while (rsCate.next()) {%>
                                <option value="<%=rsCate.getInt(1)%>" <%=(rsCate.getInt(1) == pro.getCateID() ? "selected" : "")%>>
                                    <%=rsCate.getString(2)%>
                                </option>
                                <% }%>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <input type="submit" value=<%=(req.equals("insert") ? "Register" : "Update")%> name="submit"/>
                            <input type="reset" value="Reset"/>
                        </td>
                    </tr>
                </table>
            </form>
            <%@include file="../nav/footer.jsp" %>
    </body>
</html>
