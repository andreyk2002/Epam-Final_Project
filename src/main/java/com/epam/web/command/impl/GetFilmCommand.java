package com.epam.web.command.impl;

import com.epam.web.command.Command;
import com.epam.web.command.CommandResult;
import com.epam.web.dao.DaoException;
import com.epam.web.dto.MovieDTO;
import com.epam.web.service.FilmService;
import com.epam.web.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class GetFilmCommand implements Command {
    private static final String SHOW_MOVIE = "/controller?commandName=showMoviePage";

    private final FilmService service;

    public GetFilmCommand(FilmService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String idString = request.getParameter("id");
        Long id = Long.parseLong(idString);
        HttpSession session = request.getSession();
        try {
            Optional<MovieDTO> movieOptional = service.getMovieById(id);
            movieOptional.ifPresentOrElse(movie -> session.setAttribute("movie", movie),
                    () -> request.setAttribute("errorMessage", "Movie not found"));
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return CommandResult.redirect(request.getContextPath() + SHOW_MOVIE);
    }
}
