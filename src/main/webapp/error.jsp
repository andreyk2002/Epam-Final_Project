<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="local"/>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:if test="${errorMessage!=null}">
    <div class="error">
        <h1><fmt:message key="${errorMessage}"/></h1>
    </div>
</c:if>
</body>
</html>
