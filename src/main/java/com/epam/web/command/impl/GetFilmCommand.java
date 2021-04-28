package com.epam.web.command.impl;

import com.epam.web.command.Command;
import com.epam.web.command.CommandResult;
import com.epam.web.dao.DaoException;
import com.epam.web.dto.FilmDTO;
import com.epam.web.entity.User;
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
        long id = Long.parseLong(idString);

        HttpSession session = request.getSession();
        try {
            User user = (User) session.getAttribute("user");
            long userId = user.getId();
            Optional<FilmDTO> movieOptional = service.getMovieDTOById(id, userId);
            movieOptional.ifPresentOrElse(movie -> session.setAttribute("film", movie),
                    () -> request.setAttribute("errorMessage", "local.movieNotFound"));
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return CommandResult.redirect(SHOW_MOVIE);
    }
}
