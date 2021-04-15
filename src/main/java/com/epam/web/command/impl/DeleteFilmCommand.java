package com.epam.web.command.impl;

import com.epam.web.command.Command;
import com.epam.web.command.CommandResult;
import com.epam.web.dao.DaoException;
import com.epam.web.service.FilmService;
import com.epam.web.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteFilmCommand implements Command {
    public static final String FILM_ID = "filmId";
    public static final String FIRST_PAGE = "/controller?commandName=showFilmsPage&pageNumber=0";
    private final FilmService filmService;

    public DeleteFilmCommand(FilmService deleteFilmService) {
        this.filmService = deleteFilmService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String idParam = request.getParameter(FILM_ID);
        long filmId = Long.parseLong(idParam);
        try {
            filmService.removeById(filmId);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return CommandResult.redirect(FIRST_PAGE);
    }
}
