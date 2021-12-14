<%-- 
    Document   : ViewCategory
    Created on : Oct 16, 2021, 3:20:42 AM
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
        ArrayList<Category> cate_list = (ArrayList<Category>) session.getAttribute("cate_list");
        Enumeration<Category> cate_items = Collections.enumeration(cate_list);
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
                            <a href="MngCate?service=insert">Add new</a>
                        </div>
                        <br><br>
                        <div>
                            <table class="table shopping-summery">
                                <thead>
                                    <tr class="main-hading">
                                        <th class="text-center">CATEGORY ID</th>
                                        <th class="text-center">CATEGORY NAME</th>
                                        <th class="text-center">STATUS</th>
                                        <th class="text-center" colspan="2">ACTION</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <% while (cate_items.hasMoreElements()) {
                                            Category category = cate_items.nextElement();
                                    %>
                                    <tr class="text-center">
                                        <td><%=category.getCateID()%></td>
                                        <td><%=category.getCateName()%></td>
                                        <td><%=(category.getStatus() == 1 ? "Enable" : "Disable" )%></td>
                                        <td><a href="MngCate?service=update&id=<%=category.getCateID()%>">Update</a></td>
                                        <td><a href="MngCate?service=delete&id=<%=category.getCateID()%>">Delete</a></td>
                                    </tr>
                                    <%}
                                    %>
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
