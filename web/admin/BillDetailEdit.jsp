<%-- 
    Document   : BillDetailEdit
    Created on : Oct 17, 2021, 2:22:56 AM
    Author     : chinh
--%>

<%@page import="java.math.BigDecimal"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.ResultSet"%>
<!DOCTYPE html>
<html>
    <%@include file="../nav/header.jsp" %>

    <body class="js">
        <%@include file="../nav/nav.jsp" %>

        <%
            ResultSet rsCID = (ResultSet) request.getAttribute("rsCID");
            ResultSet rs = (ResultSet) request.getAttribute("rs");
            String title = (String) request.getAttribute("title");

            String bill = request.getParameter("bill");
            String pid = "";
            String oID = "";
            int quantity = 0;
            double price = 0;
            String cid = "";
            if (rs.next()) {
                pid = rs.getString(1);
                oID = rs.getString(2);
                quantity = rs.getInt(3);
                price = rs.getDouble(6);
            }
        %>

        <%
            if (rsCID.next()) {
                cid = rsCID.getString(2);
            }
        %>
        <h3><%=cid%></h3>
        <form action="detail?bill=<%=bill%>&service=update" method="POST">
            <table border="0">
                <caption><%=title%></caption>
                <tr>
                    <td>pid</td>
                    <td><input type="text" name="pid" value="<%=pid%>" readonly/></td>
                </tr>
                <tr>
                    <td>oID</td>
                    <td><input type="text" name="oID" value="<%=oID%>" readonly/></td>
                </tr>
                <tr>
                    <td>quantity</td>
                    <td><input type="text" name="quantity" value="<%=quantity%>" /></td>
                </tr>
                <tr>
                    <td>price</td>
                    <td><input type="text" name="price" value="$<%=BigDecimal.valueOf(price).toPlainString()%>" readonly/></td>
                </tr>
                <tr>
                    <td>
                        <input type="submit" value="Update" name="submit"/>
                    </td>
                    <td>
                        <input type="reset" value="Reset"/>
                    </td>
                </tr>
            </table>
        </form>


        <%@include file="../nav/footer.jsp" %>
    </body>
</html>
