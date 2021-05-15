package com.epam.web.filter;

import com.epam.web.entity.Role;
import com.epam.web.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Set;

public class UserAccessFilter implements Filter {

    private static final String ACCESS_DENIED = "local.accessDenied";
    private final Set<String> deniedCommands = Set.of("rateFilm", "reviewFilm");


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();
        checkAccess(request, session);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private void checkAccess(HttpServletRequest request, HttpSession session) {
        User user = (User) session.getAttribute("user");
        String commandName = request.getParameter("commandName");
        if (deniedCommands.contains(commandName) && user.getRole() == Role.ADMIN) {
            request.setAttribute("errorMessage", ACCESS_DENIED);
            throw new AccessException(ACCESS_DENIED);
        }
    }

}