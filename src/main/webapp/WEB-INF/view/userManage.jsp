<%@page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="local"/>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Film rating</title>
    <link href="static/css/reset.css" rel="stylesheet"/>
    <link href="static/css/main.css" rel="stylesheet"/>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<div class="users-wrapper">
    <table class="users-table">
        <thead>
        <tr>
            <td>Action</td>
            <td>Username</td>
            <td>Rating</td>
            <td>Role</td>
            <td>Status</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${users}" var="user">
            <tr>
                <td>
                    <c:if test="${user.role == 'ADMIN'}">
                        <a class="table-link">lock</a>
                    </c:if>
                    <c:if test="${user.role != 'ADMIN'}">
                        <a class="table-link">unlock</a>
                    </c:if>
                </td>
                <td>${user.login}</td>
                <td>
                    <form action="${pageContext.request.contextPath}/controller">
                        <input type="hidden" name="commandName" value="changeUserRating">
                        <input type="hidden" name="userId" value="${user.id}">
                        <input type="number" min="0" max="100" name="rating" value="${user.rating}"/>
                        <button type="submit">OK</button>
                    </form>
                </td>
                <td>${user.role}</td>
                <td>no field here</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>
