<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Karla:ital,wght@1,500&display=swap" rel="stylesheet">
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
    <div class="content">
        <h1>Available films</h1>
        <c:forEach var="i" begin="0" end="5">
            <div class="col">
                <c:forEach var="j" begin="1" end="3">
                    <div class="card">
                        <div class="img">

                        </div>
                        <div class="other">
                            <a class="film-link">Name of film</a>
                            <br/>
                            <span>rating : 5.0</span>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </c:forEach>
    </div>
    <jsp:include page="fragments/footer.jsp"/>
</div>
</body>
</html>
