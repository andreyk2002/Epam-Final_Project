package com.epam.web.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SessionLocalizeFilter implements Filter {

    public static final String LOCAL = "local";

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        if (servletRequest.getParameter(LOCAL) != null) {
            HttpSession session = servletRequest.getSession();
            session.setAttribute(LOCAL, servletRequest.getParameter(LOCAL));
        }
        chain.doFilter(request, response);
    }

}
