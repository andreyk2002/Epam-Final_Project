package com.epam.web.command.impl;

import com.epam.web.command.Command;
import com.epam.web.command.CommandResult;
import com.epam.web.entity.User;
import com.epam.web.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class PersonalPageCommand implements Command {
    private static final String LOGIN_PAGE = "/controller?commandName=loginPage";
    private final String PAGE = "/controller?commandName=showPersonalPage";

    public PersonalPageCommand() {

    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return CommandResult.redirect(request.getContextPath() + LOGIN_PAGE);
        }
        return CommandResult.redirect(request.getContextPath() + PAGE);
    }
}
