package com.epam.web.command;

import com.epam.web.service.ServiceException;
import com.epam.web.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangeUserStatusCommand implements Command {
    private static final String PAGE = "/controller?commandName=manageUsers";
    private final UserService userService;

    public ChangeUserStatusCommand(UserService changeStatusService) {
        this.userService = changeStatusService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String userStatusParam = request.getParameter("userStatus");
        boolean blocked = Boolean.parseBoolean(userStatusParam);
        String userIdParam = request.getParameter("userId");
        long userId = Long.parseLong(userIdParam);

        userService.changeStatus(userId, blocked);
        return CommandResult.redirect(request.getContextPath() + PAGE);
    }
}
