package com.epam.web.command;

import com.epam.web.entity.Movie;
import com.epam.web.entity.User;
import com.epam.web.service.MovieService;
import com.epam.web.service.ServiceException;
import com.epam.web.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;


public class LoginCommand implements Command {

    private final static int FIRST_PAGE = 0;
    private final UserService userService;
    private final MovieService movieService;

    public LoginCommand(UserService userService, MovieService movieService) {
        this.userService = userService;
        this.movieService = movieService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Optional<User> optionalUser = userService.login(username, password);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            HttpSession session = request.getSession();
            session.setAttribute("name", user.getName());
            List<Movie>movies = movieService.getNextMovies(FIRST_PAGE);
            session.setAttribute("movies", movies);
            return CommandResult.redirect(request.getContextPath() + "/controller?commandName=mainPage&pageNumber=" + FIRST_PAGE);
        }
        HttpSession session = request.getSession();
        session.setAttribute("errorMessage", "Wrong input for user" + username);
        return CommandResult.redirect(request.getContextPath() + "/controller?commandName=loginPage");
    }
}
