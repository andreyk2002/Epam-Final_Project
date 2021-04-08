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
<h1>create new film here</h1>
<div class="dark-wrapper">
    <form class="add-film-form-admin" action="/controller?commandName=addFilm">
        <input class="film-name" type="text" value="filmName" placeholder="enter film name"/>
        <textarea class="film-description-admin" rows="5" name="filmDescription"></textarea>

        <input class="film-genres" list="genres">
        <datalist id="genres">
            <c:forEach var="genre" items="${genres}">
                <option>${genre.name}</option>
            </c:forEach>
        </datalist>
        <label class="image-load">Load image for film</label>
        <input type="file" value="filmImage"/>
    </form>
</div>



