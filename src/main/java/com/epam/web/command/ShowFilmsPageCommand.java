package com.epam.web.command;

import com.epam.web.entity.Movie;
import com.epam.web.service.MovieService;
import com.epam.web.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ShowFilmsPageCommand implements Command {

    public static final String COMMAND_URL = "/controller?commandName=mainPage&pageNumber=";
    private final MovieService movieService;

    public ShowFilmsPageCommand(MovieService movieService) {
        this.movieService = movieService;
    }


    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String page = request.getParameter("pageNumber");
        int pageNumber = Integer.parseInt(page);
        List<Movie> movies = movieService.getNextMovies(pageNumber);
        int totalPages = movieService.getPagesCount();
        HttpSession session = request.getSession();
        session.setAttribute("movies", movies);
        session.setAttribute("pagesCount", totalPages);
        return CommandResult.redirect(request.getContextPath() + COMMAND_URL + pageNumber);
    }
}
