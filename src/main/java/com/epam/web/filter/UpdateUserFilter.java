package com.epam.web.filter;

import com.epam.web.command.Command;
import com.epam.web.command.CommandFactory;
import com.epam.web.entity.User;
import com.epam.web.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UpdateUserFilter implements Filter {
    private static final Logger LOGGER = LogManager.getLogger(UpdateUserFilter.class);
    private String getUserCommand;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        getUserCommand = filterConfig.getInitParameter("getUserCommand");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        long id = user.getId();
        CommandFactory factory = new CommandFactory();
        Command command = null;
        try {
            command = factory.create(getUserCommand);
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            command.execute(request, response);
        } catch (ServiceException e) {
            LOGGER.error(e.getMessage(), e);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

}
