package com.epam.web.filter;

import com.epam.web.entity.Role;
import com.epam.web.entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Set;

public class UserAccessFilter implements Filter {

    private String errorPage;
    private final Set<String> deniedCommands = Set.of("rateFilm", "reviewFilm");

    @Override
    public void init(FilterConfig filterConfig) {
        errorPage = filterConfig.getInitParameter("errorPage");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String commandName = request.getParameter("commandName");
        if (deniedCommands.contains(commandName) && user.getRole() == Role.ADMIN) {
            request.setAttribute("errorMessage", "local.accessDenied");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(errorPage);
            requestDispatcher.forward(request, response);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

}