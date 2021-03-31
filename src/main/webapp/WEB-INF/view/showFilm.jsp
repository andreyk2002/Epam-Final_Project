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
<section class="intro" style="background: url(${movie.imagePath})">
    <div class="wrapper">
        <h1 class="into-title">${movie.name}</h1>
    </div>
</section>
<section class="about-film">
    <div class="film-wrapper-wide">
        <div class="film-description">
            <p class="film-description-text">
                ${movie.description}
            </p>
        </div>
        <div class="film-rate">
            <form action="${pageContext.request.contextPath}/controller">
                <input type="hidden" name="commandName" value="rateFilm">
                <input type="hidden" name="userID" value="${user.id}">
                <input type="hidden" name="filmID" value="${movie.id}">
                <label>Rate film:</label>
                <input class="mark" name="userMark" type="number" min="0" max="5">
                <button class="submit" type="submit">Rate</button>
            </form>
            <form class="film-review" action="${pageContext.request.contextPath}/controller">
                <input type="hidden" name="commandName" value="reviewFilm">
                <input type="hidden" name="userID" value="${user.id}">
                <input type="hidden" name="filmID" value="${movie.id}">
                <textarea name="review" class="review" rows="20" cols="40" placeholder="leave a review">
                </textarea>
                <button class="submit" type="submit">OK</button>
            </form>
        </div>
    </div>
</section>
<section class="film-reviews">
    <div class="film-wrapper-wide">
        <c:forEach begin="0" end="5">
            <div class="user-review">
                <b class="username">username : </b>
                <p class="review-content">
                    Wow! That was a really great experience.
                    Of course it one of my most favourite
                    movies. I can watch it infinite amount of times.
                </p>
            </div>
        </c:forEach>
    </div>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
