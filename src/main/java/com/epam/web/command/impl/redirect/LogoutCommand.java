package com.epam.web.command.impl.redirect;

import com.epam.web.command.Command;
import com.epam.web.command.CommandResult;
import com.epam.web.command.impl.Commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutCommand implements Command {
    private static final String LOGIN_PAGE_COMMAND = Commands.LOGIN_PAGE;

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.invalidate();
        return CommandResult.redirect(LOGIN_PAGE_COMMAND);
    }
}
