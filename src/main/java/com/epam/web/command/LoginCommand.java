package com.epam.web.command;

import com.epam.web.entity.Movie;
import com.epam.web.entity.User;
import com.epam.web.service.MovieService;
import com.epam.web.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;


public class LoginCommand implements Command {

    private final UserService userService;

    public LoginCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Optional<User> optionalUser = userService.login(username, password);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            HttpSession session = request.getSession();
            session.setAttribute("name", user.getName());
            List<Movie> movieList = MovieService.getAllMovies();
            session.setAttribute("movies", movieList);
        } else {
            request.setAttribute("errorMessage", "Wrong input");
        }
        return CommandResult.redirect(request.getContextPath() + "/controller?commandName=mainPage");
    }
}
