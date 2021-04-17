package com.epam.web.command.impl;

import com.epam.web.command.Command;
import com.epam.web.command.CommandResult;
import com.epam.web.dao.DaoException;
import com.epam.web.entity.Film;
import com.epam.web.parser.FormParser;
import com.epam.web.service.FilmService;
import com.epam.web.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class EditFilmCommand implements Command {
    private static final String FILMS_PAGE = "/controller?commandName=showFilmsPage&pageNumber=";
    private final FilmService filmService;
    private final FormParser parser;

    public EditFilmCommand(FilmService updateFilmService, FormParser parser) {
        this.filmService = updateFilmService;
        this.parser = parser;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        try {
            Film film = parser.parseFormData(request);
            filmService.updateFilm(film);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        HttpSession session = request.getSession();
        Integer pageNumber = (Integer)session.getAttribute("pageNumber");
        return CommandResult.redirect(FILMS_PAGE + pageNumber);
    }
}
