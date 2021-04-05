package com.epam.web.command;

import com.epam.web.entity.User;
import com.epam.web.service.ServiceException;
import com.epam.web.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;


public class LoginCommand implements Command {

    public static final String LOGIN_PAGE = "/controller?commandName=loginPage&errorMessage=";
    public static final String MAIN_PAGE = "/controller?commandName=showFilmsPage&pageNumber=0";
    public static final String LOGIN_ERROR = "local.loginError";
    private final UserService userService;

    public LoginCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Optional<User> optionalUser = userService.login(username, password);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            HttpSession session = request.getSession();
            session.setAttribute("name", user.getLogin());
            session.setAttribute("userId", user.getId());
            return CommandResult.redirect(request.getContextPath() + MAIN_PAGE);
        }
        return CommandResult.redirect(request.getContextPath() + LOGIN_PAGE + LOGIN_ERROR);
    }
}
