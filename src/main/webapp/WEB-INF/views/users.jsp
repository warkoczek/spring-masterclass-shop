<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>

<html lang="en">
<head>
    <title>Shop</title>
    <%@ include file="resources.jsp"%>
</head>
<body>
<%@include file="menu.jsp"%>
    <div class="container">
        <c:if test="${users.totalPages > 0}">
        <table class="table">
            <thead>
            <tr>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Email</th>
            </tr>
            </thead>
            <tbody>
                <c:forEach var="user" items="${users.data}">
                    <tr>
                        <td>${user.firstName}</td>
                        <td>${user.lastName}</td>
                        <td>${user.email}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
            <c:if test="${users.totalPages > 0}">
                <a href="show-users.html?pageNumber=${users.totalPages - 1}">Back</a>
            </c:if>
            <c:if test="${users.pageNumber + 1 < users.totalPages}">
                <a href="show-users.html?pageNumber=${users.pageNumber + 1}" class="float-right">Next</a>
            </c:if>
            <div class="text-center">${users.pageNumber + 1} / ${users.totalPages}</div>
        </c:if>

    </div>
</body>
</html>
