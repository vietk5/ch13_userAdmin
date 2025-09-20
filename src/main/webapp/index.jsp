<%-- 
    Document   : index
    Created on : Sep 20, 2025, 12:15:21 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin email</title>
        <link rel="stylesheet" href="styles/main.css" type="text/css"/>
    </head>
    <body>
        <h1>Users</h1>
        
        <table>
            <tr>
                <th>First Name</th>
                <th>Last Name</th>
                <th colspan="3">Email</th>
            </tr>
            <%--<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>--%>
            <%@ taglib prefix="c" uri="jakarta.tags.core" %>
            <c:forEach var="user" items="${users}">
            <tr>
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
                <td>${user.email}</td>
                <td> <a href="userAdmin?action=display_user&amp;email=${user.email}">Update</a></td>
                <td> <a href="userAdmin?action=delete_user&amp;userId=${user.userId}">Delete</a></td>
            </tr>
            </c:forEach>
            
        </table>
            <p><a href="userAdmin">Refresh</a></p>
    </body>
</html>
