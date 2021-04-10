package com.epam.web.filter;

import com.epam.web.entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AnonymousAccessFilter implements Filter {

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
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(loginCommand);
            requestDispatcher.forward(request, response);
        }

        if (user.isBlocked()) {
            request.setAttribute("errorMessage", "local.userBlocked");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(errorPage);
            requestDispatcher.forward(request, response);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }

}