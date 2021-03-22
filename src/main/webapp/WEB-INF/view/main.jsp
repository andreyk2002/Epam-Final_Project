<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<fmt:setBundle basename="local" />
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
            <div class="card">
                <div class="card-img">
                    <img class="movie-image" src="${movie.imageSrc}" alt="movieImage"/>
                </div>
                <div class="card-other">
                    <a class="film-link">${movie.name} : ${movie.genre}</a>
                    <br>
                    <span class="film-rating">rating ${movie.rating}</span><br>
                    <div class="film-details">
                        <a class="rate-link" href="delete-this">Rate</a>
                        <button class="view-film-button" type="submit">view film</button>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
    <jsp:include page="fragments/footer.jsp"/>
</div>
</body>
</html>
