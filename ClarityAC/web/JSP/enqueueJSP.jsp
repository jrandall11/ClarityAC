<%-- 
    Document   : enqueueJSP
    Created on : Feb 16, 2019, 9:22:02 PM
    Author     : joshrandall
--%>

<%@page import="dao.dbAccess"%>
<%@page import="model.Aircraft"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Enqueue JSP Page</title>
    </head>
    <body>
        <%
            String date = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
            String type = request.getParameter("type");
            String size = request.getParameter("size");
            Aircraft a = new Aircraft(0, date, type, size);
            dbAccess da = new dbAccess();
            da.enqueue(a);
            
            response.sendRedirect("/ClarityAC/All");
            
        %>
    </body>
</html>
