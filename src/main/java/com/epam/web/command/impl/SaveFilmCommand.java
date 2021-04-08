package com.epam.web.command.impl;

import com.epam.web.command.Command;
import com.epam.web.command.CommandResult;
import com.epam.web.dao.DaoException;
import com.epam.web.entity.Film;
import com.epam.web.service.FilmService;
import com.epam.web.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SaveFilmCommand implements Command {

    private static final String FILMS_PAGE = "showFilmsPage";
    private final FilmService filmService;

    public SaveFilmCommand(FilmService saveFilmService) {
        this.filmService = saveFilmService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String imagePath = request.getParameter("imagePath");
        String genreIdParam = request.getParameter("genreId");
        long genreId = Long.parseLong(genreIdParam);
        Film film = new Film.Builder(name, genreId)
                .withImagePath(imagePath)
                .withDescription(description)
                .build();
        try {
            filmService.saveFilm(film);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return CommandResult.redirect(request.getContextPath() + FILMS_PAGE);
    }
}
