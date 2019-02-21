<%-- 
    Document   : dequeueJSP
    Created on : Feb 18, 2019, 9:28:58 PM
    Author     : joshrandall
--%>

<%@page import="model.Aircraft"%>
<%@page import="dao.dbAccess"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dequeue</title>
    </head>
    <body>
       <%
            dbAccess da = new dbAccess();
            Aircraft ac;
            ac = da.dequeue();
            
            response.sendRedirect("/ClarityAC/All");
            
        %>
    </body>
</html>
