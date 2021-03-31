package com.epam.web.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CheckAccessFilter implements Filter {

    private String loginCommand;

    @Override
    public void init(FilterConfig filterConfig) {
        loginCommand = filterConfig.getInitParameter("loginCommand");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        String name = (String) session.getAttribute("name");
        if (name == null) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(loginCommand);
            requestDispatcher.forward(request, response);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }

}