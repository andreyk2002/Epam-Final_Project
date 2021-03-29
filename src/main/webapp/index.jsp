<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="local"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Film rating</title>
    <link href="static/css/reset.css" rel="stylesheet"/>
    <link href="static/css/main.css" rel="stylesheet"/>
</head>
<body>
<jsp:include page="WEB-INF/view/fragments/header.jsp"/>
<main>
    <section class="intro">
        <div class="wrapper">
            <h1 class="into-title"><fmt:message key="local.header"/></h1>
            <p class="into-subtitle">
                <fmt:message key="local.subtitle"/>
            </p>
            <form class="login-form" action="${pageContext.request.contextPath}/controller" method="post">
                <fieldset class="login-form-wrap">
                    <p class="form-info">
                        <input type="hidden" name="pageNumber" value="0"/>
                        <input type="hidden" name="commandName" value="login"/>
                        <input class="login-form-input" type="text" name="username"
                               placeholder="<fmt:message key="local.username"/>"/>
                        <input class="login-form-input" type="password" name="password"
                               placeholder="<fmt:message key="local.password"/>"/>
                        <button class="login-submit" type="submit"><fmt:message key="local.login"/></button>
                    </p>
                    <c:if test="${errorMessage!=null}">
                        <div class="error">
                            <h1>${errorMessage}</h1>
                        </div>
                    </c:if>
                </fieldset>
            </form>
        </div>
    </section>
    <section class="benefits">
        <div class="benefits-wrapper">
            <h2 class="benefit-title">
                <fmt:message key="local.try_site"/>
            </h2>
            <div class="benefits-cards">
                <div class="benefit-card">
                    <div class="img-card">
                        <img src="static/img/bootstrap-icons-1.4.0/film.svg" alt="film"/>
                    </div>
                    <h3 class="benefits-card-title">
                        <fmt:message key="local.benefit_first_header"/>
                    </h3>
                    <p class="benefits-info">
                        <fmt:message key="local.benefit_first"/>
                    </p>
                </div>
                <div class="benefit-card">
                    <div class="img-card">
                        <img src="static/img/bootstrap-icons-1.4.0/star-fill.svg" alt=""/>
                    </div>
                    <h3 class="benefits-card-title">
                        <fmt:message key="local.benefit_second_header"/>
                    </h3>
                    <p class="benefits-info">
                        <fmt:message key="local.benefit_second"/>
                    </p>
                </div>
                <div class="benefit-card">
                    <div class="img-card">
                        <img class="card-img" src="static/img/bootstrap-icons-1.4.0/chat.svg" alt="chat"/>
                    </div>
                    <h3 class="benefits-card-title">
                        <fmt:message key="local.benefit_third_header"/>
                    </h3>
                    <p class="benefits-info">
                        <fmt:message key="local.benefit_third"/>
                    </p>
                </div>
            </div>
        </div>
    </section>
    <section class="films-types">
        <div class="wrapper-wide">
            <h2 class="films-types-title sections-title">
                <fmt:message key="local.genres"/>
            </h2>
            <div class="types-images">
                <figure class="types-image size-lg">
                    <img class="type-image" src="static/img/science_fiction.jpg" alt="science fiction"/>
                    <figcaption class="types-img-title">
                        <fmt:message key="local.science_fiction"/></figcaption>
                </figure>
                <figure class="types-image size-lg">
                    <img class="type-image" src="static/img/comedy.jfif" alt="comedy"/>
                    <figcaption class="types-img-title"><fmt:message key="local.comedy"/></figcaption>
                </figure>
                <figure class="types-image size-small">
                    <img class="type-image" src="static/img/download.jfif" alt="action"/>
                    <figcaption class="types-img-title"><fmt:message key="local.action_film"/></figcaption>
                </figure>
                <figure class="types-image size-small">
                    <img class="type-image" src="static/img/drama.jpg" alt="drama"/>
                    <figcaption class="types-img-title"><fmt:message key="local.drama"/></figcaption>
                </figure>
                <figure class="types-image size-small">
                    <img class="type-image" src="static/img/detective.jfif" alt="detective"/>
                    <figcaption class="types-img-title"><fmt:message key="local.detective"/></figcaption>
                </figure>
            </div>
        </div>
    </section>
</main>
<jsp:include page="WEB-INF/view/fragments/footer.jsp"/>
</body>
</html>