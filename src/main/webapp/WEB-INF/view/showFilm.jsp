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
<section class="intro" style="background: url(static/img/movies/Interstellar.jpg)">
    <div class="wrapper">
        <h1 class="into-title">${film.name}</h1>
        <c:if test="${param.errorMessage!=null}">
            <div class="error">
                <h1><fmt:message key="${param.errorMessage}"/></h1>
            </div>
        </c:if>
    </div>
</section>
<section class="about-film">
    <div class="film-wrapper-wide">
        <div class="film-description">
            <p class="film-description-text">
                ${film.description}
            </p>
        </div>
        <div class="film-rate">
            <h2 class="film-current-rating"><fmt:message key="local.currentRating"/> : ${sessionScope.film.rating} </h2>
            <form action="${pageContext.request.contextPath}/controller">
                <input type="hidden" name="commandName" value="rateFilm">
                <input type="hidden" name="filmID" value="${film.id}">
                <label><fmt:message key="local.yourMark"/> </label>
                <input class="mark" name="rating" type="number" min="0" max="5">
                <button class="rate-film-button" type="submit"><fmt:message key="local.rate"/></button>
            </form>
            <form class="film-review" action="${pageContext.request.contextPath}/controller">
                <input type="hidden" name="commandName" value="reviewFilm">
                <input type="hidden" name="filmID" value="${film.id}">
                <textarea name="review" class="review" rows="20" placeholder="<fmt:message key="local.review"/>">
                </textarea>
                <button class="rate-film-button" type="submit"><fmt:message key="local.review"/></button>
            </form>
        </div>
    </div>
</section>
<section class="film-reviews">
    <div class="film-wrapper-wide">
        <c:forEach items="${sessionScope.film.filmsReviews}" var="review">
            <div class="user-review">
                <b class="username">${review.username}</b>
                <p class="review-content">
                   ${review.review}
                </p>
            </div>
        </c:forEach>
    </div>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
