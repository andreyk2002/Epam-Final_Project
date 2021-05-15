package com.epam.web.command.impl.forward;

import com.epam.web.command.Command;
import com.epam.web.command.CommandResult;
import com.epam.web.command.impl.Commands;
import com.epam.web.dao.DaoException;
import com.epam.web.dto.FilmDTO;
import com.epam.web.entity.User;
import com.epam.web.service.FilmService;
import com.epam.web.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class FilmPageCommand implements Command {
    private static final String SHOW_FILM_PAGE = Commands.SHOW_FILM_PATH.getName();

    private final FilmService service;

    public FilmPageCommand(FilmService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String idString = request.getParameter("filmId");
        long id = Long.parseLong(idString);
        HttpSession session = request.getSession();
        try {
            Optional<FilmDTO> movieOptional = service.getFilmDtoById(id);
            movieOptional.ifPresentOrElse(movie -> request.setAttribute("film", movie),
                    () -> request.setAttribute("errorMessage", "Film with id " + id + " not found"));
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return CommandResult.forward(SHOW_FILM_PAGE);
    }
}
