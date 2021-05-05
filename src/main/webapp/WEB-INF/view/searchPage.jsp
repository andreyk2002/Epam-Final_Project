<%@page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.local}"/>
<%@taglib prefix="ctg" uri="customTags" %>
<fmt:setBundle basename="local"/>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Film rating</title>
    <link href="static/css/reset.css" rel="stylesheet"/>
    <link href="static/css/main.css" rel="stylesheet"/>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<section class="intro" style="background: black; min-height: 100px; padding-bottom: 50px">
    <div class="wrapper">
        <h1 class="into-title">Results for search</h1>
        <a class="film-link" href="${pageContext.request.contextPath}/controller?commandName=showFilmsPage&pageNumber=0">
            Back to main
        </a>
    </div>
</section>
<jsp:include page="fragments/search.jsp"/>
<div class="container">
    <jsp:include page="fragments/filmList.jsp"/>
    <jsp:include page="fragments/footer.jsp"/>
</div>
</body>
<script src="static/js/dialog.js">
</script>
</html>
