package com.epam.web.filter;

import com.epam.web.entity.Role;
import com.epam.web.entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AdminAccessFilter implements Filter {

    private String errorPage;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        errorPage = filterConfig.getInitParameter("errorPage");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Role role = user.getRole();
        if (role == Role.USER) {
            request.setAttribute("errorMessage", "local.accessDenied");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(errorPage);
            requestDispatcher.forward(request, response);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
