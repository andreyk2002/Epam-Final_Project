<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="local"/>
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
                    <h2 class="film-info">${film.name}</h2>
                    <c:forEach items="${film.genres}" var="filmGenre">
                        <a class="film-link"
                           href="${pageContext.request.contextPath}/controller?commandName=searchByGenre&genreId=${filmGenre.id}">
                                ${filmGenre.name}
                        </a>
                    </c:forEach>
                    <br/>
                    <span class="film-rating"><fmt:message key="local.rating"/>: ${film.rating}</span><br>
                </div>
            </div>
        </a>

    </c:forEach>
</div>
