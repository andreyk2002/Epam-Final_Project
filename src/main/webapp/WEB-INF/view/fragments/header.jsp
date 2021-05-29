<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="local"/>
<header>
    <div class="wrapper">
        <div class="head-wrapper">
            <div class="logo">
                <span class="logo-link">
                    <img class="logo-img" src="static/img/16-logo.jpg" alt="film rating"/>
                    Top films
                </span>
            </div>
            <nav class="header-nav">
                <ul class="header-list">
                    <li class="header-item">
                        <div class="languages">
                            <form class="change-lang" action="${pageContext.request.contextPath}/controller"
                                  method="post">
                                <input class="change-lang-btn" type="submit" value="ru"/>
                                <c:if test="${empty param.get('commandName')}">
                                    <input type="hidden" name="commandName" value="loginPage"/>
                                </c:if>
                                <c:if test="${not empty param.get('commandName')}">
                                    <input type="hidden" name="commandName" value="${param.get("commandName")}"/>
                                </c:if>
                                <input type="hidden" name="local" value="ru_RU"/>
                                <c:forEach var="input" items="${param.entrySet()}">
                                    <input type="hidden" name="${input.getKey()}" value="${input.getValue()}"/>
                                </c:forEach>
                            </form>

                            <form class="change-lang" action="${pageContext.request.contextPath}/controller"
                                  method="post">
                                <input class="change-lang-btn" type="submit" value="by"/>
                                <input type="hidden" name="local" value="be_BY"/>
                                <c:if test="${empty param.get('commandName')}">
                                    <input type="hidden" name="commandName" value="loginPage"/>
                                </c:if>
                                <c:if test="${not empty param.get('commandName')}">
                                    <input type="hidden" name="commandName" value="${param.get("commandName")}"/>
                                </c:if>
                                <c:forEach var="input" items="${param.entrySet()}">
                                    <input type="hidden" name="${input.getKey()}" value="${input.getValue()}"/>
                                </c:forEach>
                            </form>

                            <form class="change-lang" action="${pageContext.request.contextPath}/controller"
                                  method="post">
                                <input class="change-lang-btn" type="submit" value="en"/>
                                <input type="hidden" name="local" value="en_US"/>
                                <c:if test="${empty param.get('commandName')}">
                                    <input type="hidden" name="commandName" value="loginPage"/>
                                </c:if>
                                <c:if test="${not empty param.get('commandName')}">
                                    <input type="hidden" name="commandName" value="${param.get("commandName")}"/>
                                </c:if>
                                <c:forEach var="input" items="${param.entrySet()}">
                                    <input type="hidden" name="${input.getKey()}" value="${input.getValue()}"/>
                                </c:forEach>
                            </form>
                        </div>
                    </li>
                    <c:if test="${not empty sessionScope.user}">
                        <li class="header-item">
                            <a href="${pageContext.request.contextPath}/controller?commandName=showFilmsPage&pageNumber=0"
                               class="logo-link">
                                <fmt:message key="local.main"/>
                            </a>
                        </li>
                        <li class="header-item">
                            <a href="${pageContext.request.contextPath}/controller?commandName=updateUser"
                               class="logo-link">
                                <fmt:message key="local.personal"/>
                            </a>
                        </li>
                        <c:if test="${sessionScope.user.role == 'ADMIN'}">
                            <li class="header-item">
                                <a href="${pageContext.request.contextPath}/controller?commandName=manageUsers"
                                   class="logo-link">
                                    <fmt:message key="local.manageUsers"/>
                                </a>
                            </li>
                        </c:if>
                        <li class="header-item">
                            <a href="${pageContext.request.contextPath}/controller?commandName=logout"
                               class="logo-link">
                                <fmt:message key="local.logout"/>
                            </a>
                        </li>
                    </c:if>
                </ul>
                <div id="cross" class="header-nav-close">
                    <span class="header-nav-close-line">
                    </span>
                    <span class="header-nav-close-line">
                    </span>
                </div>
            </nav>
            <div class="header-burger">
                <a>
                    <img id="open-menu" class="menu-list-img" src="static/img/bootstrap-icons-1.4.0/list.svg" alt=""/>
                </a>
            </div>
        </div>
    </div>
</header>
<script src="static/js/header.js"></script>


