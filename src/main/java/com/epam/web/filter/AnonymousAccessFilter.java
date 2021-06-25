package com.epam.web.filter;

import com.epam.web.entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Set;

public class AnonymousAccessFilter implements Filter {
    private static final Set<String> allowedCommands = Set.of("login", "changeLanguage", "loginPage", "logout");
    private String errorPage;
    private String loginCommand;

    @Override
    public void init(FilterConfig filterConfig) {
        errorPage = filterConfig.getInitParameter("errorPage");
        loginCommand = filterConfig.getInitParameter("loginCommand");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String commandName = request.getParameter("commandName");
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        if (!allowedCommands.contains(commandName)) {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            if (user == null) {
                response.sendRedirect(request.getContextPath() + "?commandName=" + loginCommand);
                return;
            }

            if (user.isBlocked()) {
                request.setAttribute("errorMessage", "local.userBlocked");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher(errorPage);
                requestDispatcher.forward(request, response);
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

}
