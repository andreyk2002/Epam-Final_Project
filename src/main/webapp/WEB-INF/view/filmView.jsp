<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 3/19/2021
  Time: 8:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<section class="intro">
    <div class="wrapper">
        <h1 class="into-title">Perfect place for film lovers</h1>
        <p class="into-subtitle">
            Share your film experience and emotions with other users,
            leave helpful reviews and earn rating points to become true film expert!
        </p>
        <form class="login-form" action="${pageContext.request.contextPath}/controller" method="post">
            <fieldset class="login-form-wrap">
                <p class="form-info">
                    <input type="hidden" name="commandName" value="register"/>
                    <input class="login-form-input" type="text" name="username" placeholder="username"/>
                    <input class="login-form-input" type="password" name="password"
                           placeholder="password"/>
                    <input class="login-form-input" type="password" name="password-repeat"
                           placeholder="repeat password"/>
                    <button class="login-submit" type="submit">login</button>
                </p>
            </fieldset>
        </form>
    </div>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
