<%-- 
    Document   : EditCategory
    Created on : Oct 16, 2021, 3:21:00 AM
    Author     : chinh
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="Entity.Category"%>
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
                        <div class="form-main">
                            <%
                                String req = (String) request.getParameter("service");

                                String title = (String) session.getAttribute("title");

                                Category category = new Category(0, "", 1);

                                if (req.equals("update")) {
                                    category = (Category) session.getAttribute("category");
                                }
                            %>

                            <%
                                if (req.equals("insert")) {
                            %>
                            <form action="MngCate?service=insert" method="POST">
                                <% }%>

                                <%
                                    if (req.equals("update")) {
                                %>
                                <form action="MngCate?service=update" method="POST">
                                    <% }%>
                                    <h3><%=title%></h3><br>
                                    <table border='0'>
                                        <%
                                            if (req.equals("update")) {
                                        %>
                                        <tr>
                                            <td>cateID:</td>
                                            <td><input type='text' name='cateID' value='<%=category.getCateID()%>' readonly/></td>
                                        </tr>
                                        <% }%>
                                        <tr>
                                            <td>cateName:</td>
                                            <td><input type='text' name='cateName' value='<%=category.getCateName()%>' /></td>
                                        </tr>
                                        <tr>
                                            <td>Status:</td>
                                            <td>
                                                <input type="radio" name="status" value="1" <%=(category.getStatus() == 1 ? "checked" : "")%>/>Enable
                                                <input type="radio" name="status" value="0" <%=(category.getStatus() == 0 ? "checked" : "")%>/>Disable
                                            </td>
                                        </tr>
                                        <tr>
                                            <td colspan="2">
                                                <input type="submit" value=<%=(req.equals("insert") ? "Register" : "Update")%> name="submit" class="btn"/>
                                                <input type="reset" value="Reset" class="btn"/>
                                            </td>
                                        </tr>
                                    </table>
                                </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="../nav/footer.jsp" %>
    </body>
</html>
