<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 3/13/2021
  Time: 4:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<a href="#" id="menuBtn">
</a>
<div id="menu">

    <nav class="menu-list">
        <a class="menu-link" href="${pageContext.request.contextPath}/controller">Main</a>
        <a class="menu-link" href="${pageContext.request.contextPath}/register">Register</a>
        <a class="menu-link" href="">Login</a>
    </nav>
</div>
<script>
    const ACTIVE_TAG = "menu_active"
    let menuOpened = false
    let menuBtn = document.getElementById("menuBtn")
    let menu = document.getElementById("menu")
    menuBtn.addEventListener('click', function (event) {
        event.preventDefault();
        if (!menuOpened) {
            menu.classList.add(ACTIVE_TAG)
            menuOpened = true;
        } else {
            menu.classList.remove(ACTIVE_TAG)
            menuOpened = false;
        }
    })
</script>
