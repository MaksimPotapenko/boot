<%-- 
    Document   : listboots
    Created on : 25.03.2022, 9:13:51
    Author     : user
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Список обуви</title>
    </head>
    <body>
        <h1>Список обуви</h1>
        <select name="listboots" multiple="true">
            <c:forEach var="sneaker" items="${sneakers}">
                <option value="${sneaker.id}">${sneaker.SneakerModel}. ${sneaker.SneakerSize}. 
                </option>
            </c:forEach>
        </select>
    </body>
</html>