package com.epam.web.command.impl.redirect;

import com.epam.web.command.Command;
import com.epam.web.command.CommandResult;
import com.epam.web.command.impl.Commands;
import com.epam.web.service.FilmService;
import com.epam.web.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteFilmCommand implements Command {
    private static final String FILM_ID = "filmId";
    private final FilmService filmService;

    public DeleteFilmCommand(FilmService deleteFilmService) {
        this.filmService = deleteFilmService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String idParam = request.getParameter(FILM_ID);
        long filmId = Long.parseLong(idParam);
        filmService.removeById(filmId);
        return CommandResult.redirect(Commands.FILMS_PAGE);
    }
}
