<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="local"/>
<div class="add-film">
    <div class="search-films">
        <form class="name-search">
            <input type="hidden" name="commandName" value="searchFilm"/>
            <input id="searchInput" name="searchString" type="search" placeholder="<fmt:message key="local.search"/>">
            <button class="search" type="submit">
                <img src="static/img/bootstrap-icons-1.4.0/search.svg">
            </button>
        </form>
        <form class="genre-search">
            <input type="hidden" name="commandName" value="searchByGenre"/>
            <select class="genres-options" name="genreName">
                <c:forEach items="${genres}" var="genreId">
                    <option value="${genre.name}">${genre.id}</option>
                </c:forEach>
            </select>
            <button class="search" type="submit">
                <img src="static/img/bootstrap-icons-1.4.0/search.svg">
            </button>
        </form>
    </div>
    <c:if test="${user.role == 'ADMIN'}">
        <form class="add-film-form" action="${pageContext.request.contextPath}/controller">
            <input type="hidden" name="commandName" value="addFilm">
            <button class="add-film-btn" type="submit">
                <fmt:message key="local.addFilm"/>
            </button>
        </form>
    </c:if>
</div>
