package com.epam.web.command.impl;

import com.epam.web.command.Command;
import com.epam.web.command.CommandResult;
import com.epam.web.entity.User;
import com.epam.web.service.ServiceException;
import com.epam.web.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class GetUserCommand implements Command {
    public static final String USER = "user";
    public static final String PERSONAL_PAGE = Commands.PERSONAL_PAGE_COMMAND.getName();
    private  final UserService service;

    public GetUserCommand(UserService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute(USER);
        long id = currentUser.getId();
        Optional<User> optionalUser = service.getUserById(id);
        optionalUser.ifPresent(user -> session.setAttribute(USER, user ));
        optionalUser.orElseThrow(() -> new ServiceException("local.userNotFound"));
        return CommandResult.redirect(PERSONAL_PAGE);
    }
}
