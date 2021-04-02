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
<section class="intro">
    <div class="wrapper">
        <h1 class="into-title"><fmt:message key="local.personal"/></h1>
    </div>
</section>
<section class="benefits">
    <div class="personal-wrapper">
        <h2 class="personal-title">
            <fmt:message key="Username"/>
        </h2>
        <div>
            <h3 class="personal-info">Rating: 50</h3>
            <h3 class="personal-info">Role: admin</h3>
        </div>
    </div>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
