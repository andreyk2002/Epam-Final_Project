package com.epam.web.command;

import com.epam.web.dao.DaoException;
import com.epam.web.entity.Movie;
import com.epam.web.service.MovieService;
import com.epam.web.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class FilmShowCommand implements Command {
    public static final String SHOW_MOVIE = "/controller?commandName=showMoviePage";

    private final MovieService service;

    public FilmShowCommand(MovieService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String idString = request.getParameter("id");
        Long id = Long.parseLong(idString);
        HttpSession session = request.getSession();
        try {
            Optional<Movie> movieOptional = service.getMovieById(id);
            movieOptional.ifPresentOrElse(movie -> session.setAttribute("movie", movie),
                    () -> session.setAttribute("errorMessage", "Movie not found"));
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return CommandResult.redirect(request.getContextPath() + SHOW_MOVIE);
    }
}
