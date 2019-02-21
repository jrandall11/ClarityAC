<%-- 
    Document   : all
    Created on : Feb 17, 2019, 6:55:34 PM
    Author     : joshrandall
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
    <head>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>All Aircraft</title>
    </head>
    <body>
        <div style="width: 1200px; margin-left: auto; margin-right: auto;">
            <h1>AC Queue</h1>
            <table cellpadding="10">
                <tr>
                    <th>Id</th>
                    <th>Date</th>
                    <th>Type</th>
                    <th>Size</th>
                    <th></th>
                </tr>
                <c:forEach items="${allAC}" var="a">
                    <tr>
                        <td>${a.id}</td>
                        <td>${a.date.substring(0,16)}</td>
                        <td>${a.type}</td>
                        <td>${a.size}</td>
                    </tr>
                </c:forEach>
            </table>
            <table>
                <tr>
                    <a class="btn btn-primary" href="/ClarityAC/enqueue.html" role="button">Enqueue</a>
                    <form action="JSP/dequeueJSP.jsp" method="Post">
                        <button type="submit" class="btn btn-primary">Dequeue</button>
                    </form>
                </tr>
            <table>
        </div>
    </body>
</html>
