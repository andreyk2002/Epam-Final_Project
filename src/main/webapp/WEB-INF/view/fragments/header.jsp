<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="local"/>
<header>
    <div class="wrapper">
        <div class="head-wrapper">
            <div class="logo">
                <a href="/" class="logo-link">
                    <img class="logo-img" src="static/img/16-logo.jpg" alt="film rating"/>
                    Top films
                </a>
            </div>
            <nav class="header-nav">
                <ul class="header-list">
                    <li class="header-item">
                        <div class="languages">
                            <form class="change-lang" action="${pageContext.request.contextPath}/controller"
                                  method="post">
                                <input class="change-lang-btn" type="submit" value="ru"/>
                                <input type="hidden" name="local" value="ru_RU"/>
                                <input type="hidden" name="commandName" value="changeLanguage"/>
                                <input type="hidden" name="currentPage" value="${param.get("commandName")}"/>
                                <input type="hidden" name="pageNumber" value="${param.get("pageNumber")}"/>
                            </form>

                            <form class="change-lang" action="${pageContext.request.contextPath}/controller"
                                  method="post">
                                <input class="change-lang-btn" type="submit" value="by"/>
                                <input type="hidden" name="local" value="be_BY"/>
                                <input type="hidden" name="commandName" value="changeLanguage"/>
                                <input type="hidden" name="currentPage" value="${param.get("commandName")}"/>
                                <input type="hidden" name="pageNumber" value="${param.get("pageNumber")}"/>
                            </form>

                            <form class="change-lang" action="${pageContext.request.contextPath}/controller"
                                  method="post">
                                <input class="change-lang-btn" type="submit" value="en"/>
                                <input type="hidden" name="local" value="en_US"/>
                                <input type="hidden" name="commandName" value="changeLanguage"/>
                                <input type="hidden" name="currentPage" value="${param.get("commandName")}"/>
                                <input type="hidden" name="pageNumber" value="${param.get("pageNumber")}"/>
                            </form>
                        </div>
                    </li>

                    <li class="header-item">
                        <a href="${pageContext.request.contextPath}/controller?commandName=showFilmsPage&pageNumber=0"
                           class="logo-link">
                            <fmt:message key="local.main"/>
                        </a>
                    </li>
                    <li class="header-item">
                        <a href="${pageContext.request.contextPath}/controller?commandName=personalPage"
                           class="logo-link">
                            <fmt:message key="local.personal"/>
                        </a>
                    </li>
                    <li class="header-item">
                        <a href="${pageContext.request.contextPath}/controller?commandName=logout" class="logo-link">
                            <fmt:message key="local.logout"/>
                        </a>
                    </li>
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


