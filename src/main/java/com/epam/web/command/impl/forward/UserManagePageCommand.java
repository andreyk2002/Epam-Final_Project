package com.epam.web.command.impl.forward;

import com.epam.web.command.Command;
import com.epam.web.command.CommandResult;
import com.epam.web.entity.User;
import com.epam.web.service.ServiceException;
import com.epam.web.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class UserManagePageCommand implements Command {


    private static final String USER_MANAGE_PAGE = "/WEB-INF/view/userManage.jsp";
    private final UserService userService;

    public UserManagePageCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        List<User> allUsers = userService.getAllUsers();
        request.setAttribute("users", allUsers);
        return CommandResult.forward(USER_MANAGE_PAGE);
    }
}
