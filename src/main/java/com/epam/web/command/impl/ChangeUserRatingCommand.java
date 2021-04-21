package com.epam.web.command.impl;

import com.epam.web.command.Command;
import com.epam.web.command.CommandResult;
import com.epam.web.service.ServiceException;
import com.epam.web.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangeUserRatingCommand implements Command {
    private static final String MANAGE_USERS = "/controller?commandName=manageUsers";
    private final UserService userService;

    public ChangeUserRatingCommand(UserService changeRatingService) {
        this.userService = changeRatingService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String userIdParam = request.getParameter("userId");
        long userId = Long.parseLong(userIdParam);
        String newRatingParam = request.getParameter("rating");
        double newRating = Double.parseDouble(newRatingParam);
        boolean isRated = userService.changeRating(userId, newRating);
        return CommandResult.redirect(MANAGE_USERS);
    }
}
