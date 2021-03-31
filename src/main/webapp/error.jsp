<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<input type="number" m>
<c:if test="${errorMessage!=null}">
    <div class="error">
        <h1>${errorMessage}</h1>
    </div>
</c:if>
</body>
</html>
