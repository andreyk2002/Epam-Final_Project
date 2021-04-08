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
        <h2>Add film</h2>
        <input type="hidden" name="commandName" value="saveFilm"/>
        <input required class="film-name film-input" name="filmName" type="text" placeholder="enter film name"/>
        <textarea class="film-description-admin film-input" rows="5" name="filmDescription"></textarea>
        <br/>
        <select required class="genres-select" name="genreId">
            <c:forEach items="${genres}" var="genre">
                <option value="${genre.id}">${genre.name}</option>
            </c:forEach>
        </select>
        <br/>
        <label class="image-load">Load image for film</label>
        <br/>
        <input name="filmImage" type="file" style="color: white"/>
        <button type="submit" class="rate-film-button">Create</button>
    </form>
</div>



