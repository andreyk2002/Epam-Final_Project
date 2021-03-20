<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 3/13/2021
  Time: 3:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                        <a href="${pageContext.request.contextPath}/controller?commandName=mainPage" class="logo-link">Main</a>
                    </li>
                    <li class="header-item">
                        <a href="${pageContext.request.contextPath}/controller?commandName=personalPage" class="logo-link">Personal page</a>
                    </li>
                    <li class="header-item">
                        <a href="${pageContext.request.contextPath}/controller?commandName=logout" class="logo-link">Login/Logout</a>
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
                    <img id="open-menu" class="menu-list-img" src="static/img/bootstrap-icons-1.4.0/list.svg"/>
                </a>
            </div>
        </div>
    </div>
</header>
<script src="static/js/header.js"></script>


