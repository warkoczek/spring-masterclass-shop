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
    <table class="table">
    <thead>
        <tr>
            <th>Name</th>
            <th>Description</th>
            <th>Price</th>
            <th>Type</th>
        </tr>
    </thead>
        <tbody>
        <c:forEach var="product" items="${products.data}">
            <tr>
                <td>${product.name}</td>
                <td>${product.description}</td>
                <td>${product.price}</td>
                <td>${product.type}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</div>
</body>
</html>
