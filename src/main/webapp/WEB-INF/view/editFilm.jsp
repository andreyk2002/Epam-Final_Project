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
<body class="dark-body">
<jsp:include page="fragments/header.jsp"/>
<div class="dark-wrapper">
    <form method="post" enctype="multipart/form-data" class="add-film-form-admin"
          action="${pageContext.request.contextPath}/controller?commandName=updateFilm">
        <h2 class="add-film-title">
            <fmt:message key="local.changeFilm"/> ${movie.name}
        </h2>
        <input type="hidden" name="filmId" value="${movie.id}"/>
        <input type="hidden" name="image_path" value="${movie.imagePath}"/>
        <label class="edit-label"><fmt:message key="local.changeName"/>:</label>
        <input class="film-name film-input" name="name" type="text" value="${movie.name}"/>

        <br>
        <label class="edit-label"><fmt:message key="local.filmDescription"/></label>
        <br>
        <textarea class="film-description-admin film-input" rows="15" name="description"
                  value="${movie.description}">${movie.description}</textarea>
        <br/>
        <label class="edit-label"><fmt:message key="local.changeGenre"/>:</label>
        <br/>
        <c:forEach items="${genres}" var="genre">
            <label style="color: white">
                    ${genre.name}
                <c:if test="${movie.genres.contains(genre)}">
                    <input class="film-checkbox" checked type="checkbox" name="genre" value="${genre.id}">
                </c:if>
                <c:if test="${!movie.genres.contains(genre)}">
                    <input class="film-checkbox" type="checkbox" name="genre" value="${genre.id}">
                </c:if>
            </label>
            <br>
        </c:forEach>
        <br/>
        <label class="edit-label"><fmt:message key="local.changeImage"/></label>
        <br/>
        <input id="imageUpload" class="film-input" name="Image" type="file" accept="image/*"
               style="color: white"/>
        <button type="submit" class="rate-film-button">OK</button>
    </form>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>
<script src="static/js/checkboxes.js">
</script>
</html>
