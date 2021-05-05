package com.epam.web.command.impl;

import com.epam.web.command.Command;
import com.epam.web.command.CommandResult;
import com.epam.web.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RedirectToPageCommand implements Command {

    private final String redirectCommand;

    public RedirectToPageCommand(String redirectCommand) {
        this.redirectCommand = redirectCommand;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        return CommandResult.redirect("/controller?commandName=" + redirectCommand);
    }
}
