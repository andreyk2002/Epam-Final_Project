<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="static/css/style.css" rel="stylesheet"/>
    <title>Title</title>
</head>
<body>
<div class="container">
    <jsp:include page="fragments/header.jsp"/>
    <jsp:include page="fragments/menu.jsp"/>


    <c:if test="${name != null}">
        <div class="greeting">
            <h2>Hello, ${name}</h2>
        </div>
    </c:if>
    <c:if test="${errorMessage != null}">
        <div class="error">
                ${errorMessage}
        </div>
    </c:if>

    <jsp:include page="fragments/footer.jsp"/>
</div>
</body>
</html>
