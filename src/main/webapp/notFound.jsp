<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
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
<jsp:include page="WEB-INF/view/fragments/header.jsp"/>
<body style="background-color: grey">
<div class="error-wrapper">
   <h1 class="error-header">404 : <fmt:message key="local.notFound"/> </h1>
</div>
</body>
</html>
