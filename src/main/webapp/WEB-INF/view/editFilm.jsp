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
<div class="dark-wrapper">
    <form method="post" class="add-film-form-admin" action="${pageContext.request.contextPath}/controller">
        <h2 class="add-film-title">Change film ${movie.name}</h2>
        <input type="hidden" name="commandName" value="editFilm">
        <input type="hidden" name="filmId" value="${movie.id}"/>
        <input class="film-name film-input" name="name" type="text" value="${movie.name}"/>
        <textarea class="film-description-admin film-input" rows="5" name="description"
                 value="${movie.description}"></textarea>
        <br/>
        <select class="genres-select" value="${movie.genreId}" name="genre_id">
            <c:forEach items="${genres}" var="genre">
                <option value="${genre.id}">${genre.name}</option>
            </c:forEach>
        </select>
        <br/>
        <button type="submit" class="rate-film-button">OK</button>
    </form>
</div>
</body>
</html>
