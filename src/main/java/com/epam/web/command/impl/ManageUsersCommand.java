package com.epam.web.command.impl;

import com.epam.web.command.Command;
import com.epam.web.command.CommandResult;
import com.epam.web.entity.User;
import com.epam.web.service.ServiceException;
import com.epam.web.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ManageUsersCommand implements Command {

    private static final String PAGE = "/controller?commandName=userManagePage";
    private final UserService userService;

    public ManageUsersCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        List<User> allUsers = userService.getAllUsers();
        session.setAttribute("users", allUsers);
        return CommandResult.redirect(request.getContextPath() + PAGE);
    }
}
