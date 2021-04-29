<%@page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.local}"/>
<%@taglib prefix="ctg" uri="customTags" %>
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
<section class="intro">
    <div class="wrapper">
        <h1 class="into-title"><fmt:message key="local.avaible_films"/></h1>
        <c:if test="${sessionScope.user.login != null}">
            <h2 class="greeting"><fmt:message key="local.hello"/>, ${sessionScope.user.login}</h2>
        </c:if>
    </div>
</section>

<div class="add-film">
    <form class="search-films">
        <input type="hidden" name="commandName" value="searchFilm"/>
        <input id="searchInput" name="searchString" type="search" placeholder="<fmt:message key="local.search"/>">
        <button class="search" type="submit">
            <img src="static/img/bootstrap-icons-1.4.0/search.svg">
        </button>
    </form>
    <c:if test="${user.role == 'ADMIN'}">
        <form class="add-film-form" action="${pageContext.request.contextPath}/controller">
            <input type="hidden" name="commandName" value="addFilm">
            <button class="add-film-btn" type="submit">
                <fmt:message key="local.addFilm"/>
            </button>
        </form>
    </c:if>
</div>
<div class="container">
    <div class="wrapper-wide">
        <c:forEach items="${sessionScope.movies}" var="film">
            <a class="film-view-link"
               href="${pageContext.request.contextPath}/controller?commandName=movie&id=${film.id}">
                <c:if test="${user.role == 'ADMIN'}">
                    <div class="manage-film">
                        <form class="manage-film-form" action="${pageContext.request.contextPath}/controller">
                            <input type="hidden" name="commandName" value="deleteFilm"/>
                            <!-- storing locale to localize confirmation messaging in delete dialog-->
                            <input type="hidden" name="locale" value="${sessionScope.local}"/>
                            <input type="hidden" name="filmId" value="${film.id}"/>
                            <button class="film-edit delete-button" type="submit">
                                <img class="film-edit-btn" src="static/img/bootstrap-icons-1.4.0/trash.svg"/>
                            </button>
                        </form>
                        <form class="manage-film-form" action="${pageContext.request.contextPath}/controller">
                            <input type="hidden" name="commandName" value="editFilm"/>
                            <input type="hidden" name="filmId" value="${film.id}"/>
                            <button class="film-edit" type="submit">
                                <img class="film-edit-btn" src="static/img/bootstrap-icons-1.4.0/pencil-fill.svg"/>
                            </button>
                        </form>
                    </div>
                </c:if>
                <div class="card">
                    <div class="card-img">
                        <img class="film-image" src="${film.imagePath}" alt="movieImage"/>
                    </div>
                    <div class="card-other">
                        <h2 class="film-link">${film.name}</h2>
                        <h2 class="film-link">${film.genre}</h2>
                        <span class="film-rating"><fmt:message key="local.rating"/>: ${film.rating}</span><br>
                    </div>
                </div>
            </a>

        </c:forEach>
        <ctg:pagingTag pagesCount="${pagesCount}" currentPage="${pageNumber}"/>
    </div>
    <jsp:include page="fragments/footer.jsp"/>
</div>
</body>
<script src="static/js/dialog.js">

</script>
</html>
