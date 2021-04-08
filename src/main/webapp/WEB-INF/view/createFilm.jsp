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
    <form class="add-film-form-admin" action="${pageContext.request.contextPath}/controller">
        <input name="commandName" value="saveFilm"/>
        <input class="film-name film-input" name="filmName" type="text" placeholder="enter film name"/>
        <textarea class="film-description-admin film-input" rows="5" name="filmDescription"></textarea>
        <input name="filmGenre" class="film-genres film-input" list="genres"/>
        <datalist id="genres">
            <c:forEach var="genre" items="${genres}">
                <option>${genre.name}</option>
            </c:forEach>
        </datalist>
        <br/>
        <label class="image-load">Load image for film</label>
        <br/>
        <input name="filmImage" type="file"/>
        <button type="submit" class="rate-film-button">Create</button>
    </form>
</div>



