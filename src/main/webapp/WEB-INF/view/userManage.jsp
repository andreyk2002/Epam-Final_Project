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
<div class="dark-wrapper">
    <table class="users-table">
        <thead>
        <tr>
            <td><fmt:message key="local.on_user_action"/></td>
            <td><fmt:message key="local.username"/></td>
            <td><fmt:message key="local.rating"/></td>
            <td><fmt:message key="local.role"/></td>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${users}" var="user">
            <tr>
                <td>
                    <form action="${pageContext.request.contextPath}/controller">
                        <input type="hidden" name="commandName"  value="changeUserStatus">
                        <input type="hidden" name="userStatus" value="${user.blocked}">
                        <input type="hidden" name="userId" value="${user.id}">
                        <button type="submit">
                            <c:if test="${user.blocked}">
                                <fmt:message key="local.unblock"/>
                            </c:if>
                            <c:if test="${!user.blocked}">
                                <fmt:message key="local.block"/>
                            </c:if>
                        </button>
                    </form>
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
                <td><fmt:message key="local.${user.role}"/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
