<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
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
        <h1 class="into-title"><fmt:message key="local.avaible_films"/></h1>
        <c:if test="${name != null}">
            <h2 class="greeting"><fmt:message key="local.hello"/>, ${name}</h2>
        </c:if>
    </div>
</section>
<div class="container">
    <div class="wrapper-wide">
        <c:forEach items="${sessionScope.movies}" var="movie">
            <a class="film-view-link"
               href="${pageContext.request.contextPath}/controller?commandName=filmPage&id=${movie.id}">
                <div class="card">
                    <div class="card-img">
                        <img class="movie-image" src="${movie.imagePath}" alt="movieImage"/>
                    </div>
                    <div class="card-other">
                        <h2 class="film-link">${movie.name} : Test</h2>
                        <br/>
                        <br/>
                        <span class="film-rating">rating : -1</span><br>
                    </div>
                </div>
            </a>
        </c:forEach>
        <div class="pages">
            <c:forEach var="i" begin="0" end="${sessionScope.pagesCount}">
            <a class="film-link"
               href="${pageContext.request.contextPath}/controller?commandName=showFilmsPage&pageNumber=${i}">${i}</a>
            </c:forEach>
        </div>
    </div>
    <jsp:include page="fragments/footer.jsp"/>
</div>
</body>
</html>
