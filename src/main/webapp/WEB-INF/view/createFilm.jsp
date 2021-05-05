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
    <form method="post" enctype="multipart/form-data" class="add-film-form-admin"
          action="${pageContext.request.contextPath}/controller?commandName=saveFilm">
        <h2 class="add-film-title"><fmt:message key="local.addFilm"/></h2>
        <input required class="film-name film-input" name="name" type="text"
               placeholder="<fmt:message key="local.enterName"/>"/>
        <textarea class="film-description-admin film-input" rows="15" name="description"
                  placeholder="<fmt:message key="local.enterDescription"/>"></textarea>
        <br/>

        <c:forEach items="${genres}" var="genre">
            <label style="color: white">
                ${genre.name}
                <input type="checkbox" name="genre" value="${genre.id}">
            </label>
            <br>
        </c:forEach>

        <br/>
        <label class="image-load"><fmt:message key="local.filmImageLoad"/></label>
        <br/>
        <input required class="film-input" name="Image" type="file" accept="image/*" style="color: white"/>
        <br/>
        <button type="submit" class="rate-film-button"><fmt:message key="local.filmCreate"/></button>
    </form>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>