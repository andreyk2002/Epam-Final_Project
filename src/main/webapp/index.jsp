<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 3/13/2021
  Time: 2:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="static/css/style.css" rel="stylesheet"/>
    <title>Title</title>
</head>
<body>

<div class="container">
    <jsp:include page="WEB-INF/view/fragments/header.jsp"/>
    <jsp:include page="WEB-INF/view/fragments/menu.jsp"/>
    <div class="main">
        <h2>Login in the system</h2>
        <form action="${pageContext.request.contextPath}/controller" method="post">
            <input type="hidden" name="commandName" value="login"/>
            <label>Username</label>
            <input type="text" name="username"/>
            <br/>
            <label>Password</label>
            <input type="password" name="password"/>
            <br/>
            <input type="submit" value="submit"/>
        </form>
    </div>
    <jsp:include page="WEB-INF/view/fragments/footer.jsp"/>
</div>
</body>
</html>
