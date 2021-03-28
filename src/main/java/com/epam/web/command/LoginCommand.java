package com.epam.web.command;

import com.epam.web.entity.User;
import com.epam.web.service.ServiceException;
import com.epam.web.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;


public class LoginCommand implements Command {

    private final static int FIRST_PAGE = 0;
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
            return CommandResult.redirect(request.getContextPath() + "/controller?commandName=showFilmsPage&pageNumber=" + FIRST_PAGE);
        }
        HttpSession session = request.getSession();
        //TODO: error message should be internalized
        session.setAttribute("errorMessage", "Wrong input for user " + username);
        return CommandResult.redirect(request.getContextPath() + "/controller?commandName=loginPage");
    }
}
