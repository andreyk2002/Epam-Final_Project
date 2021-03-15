<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 3/15/2021
  Time: 10:38 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
    <link href="static/css/style.css" rel="stylesheet">
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<jsp:include page="fragments/menu.jsp"/>
<h2>Register</h2>
<div class="form">
    <form class="register-form" action="${pageContext.request.contextPath}/register" method="post">
        <input type="text" placeholder="name"/>
        <input type="password" placeholder="password"/>
        <input type="text" placeholder="email address"/>
        <button>create</button>
    </form>
</div>
<p class="message">Already registered? <a href="${pageContext.request.contextPath}/controller">Sign In</a></p>
<jsp:include page="fragments/footer.jsp"/>

</body>
</html>
