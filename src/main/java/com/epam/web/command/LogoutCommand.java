package com.epam.web.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutCommand implements Command{
    private static final String LOGIN_PAGE_COMMAND = "/controller?commandName=loginPage";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().invalidate();
        return CommandResult.redirect(request.getContextPath() + LOGIN_PAGE_COMMAND);
    }
}
