<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <h1 class="into-title">Perfect place for film lovers</h1>
        <p class="into-subtitle">
            Share your film experience and emotions with other users,
            leave helpful reviews and earn rating points to become true film expert!
        </p>
    </div>
</section>
<div class="container">
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
    <h1>Available films</h1>
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
