<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 3/13/2021
  Time: 2:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Roboto+Mono:wght@500&display=swap" rel="stylesheet">
    <link href="static/css/style.css" rel="stylesheet"/>
    <title>Title</title>
</head>
<body>

<div class="container">
    <jsp:include page="WEB-INF/view/fragments/header.jsp"/>
    <jsp:include page="WEB-INF/view/fragments/menu.jsp"/>
    <div class="login-page">
        <div class="form">
            <form class="register-form" action="${pageContext.request.contextPath}/controller" method="post">
                <input type="hidden" name="commandName" value="login"/>
                <input type="text" name="username" placeholder="username"/>
                <input type="password" name="password" placeholder="password"/>
                <button type="submit">login</button>
            </form>
        </div>
    </div>
    <p class="message">Don't have an account? You can register
        <a href="${pageContext.request.contextPath}/register">here</a>
    </p>
    <jsp:include page="WEB-INF/view/fragments/footer.jsp"/>
</div>
</body>
</html>
