package com.epam.web.command;

import com.epam.web.entity.User;
import com.epam.web.service.ServiceException;
import com.epam.web.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class PersonalPageCommand implements Command {
    private static final String LOGIN_PAGE = "/controller?commandName=loginPage";
    private final UserService userService;
    private final String PAGE = "/controller?commandName=showPersonalPage";

    public PersonalPageCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute("userId");
        if(userId == null){
            return CommandResult.redirect(request.getContextPath() + LOGIN_PAGE);
        }
        Optional<User> optionalUser = userService.getUserById(userId);
        optionalUser.ifPresent(user -> session.setAttribute("user", user));
        optionalUser.orElseThrow(() -> new ServiceException("no user find"));
        return CommandResult.redirect(request.getContextPath() + PAGE);
    }
}
