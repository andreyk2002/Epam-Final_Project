package com.epam.web.command.impl;

import com.epam.web.command.Command;
import com.epam.web.command.CommandResult;
import com.epam.web.dao.DaoException;
import com.epam.web.entity.Film;
import com.epam.web.service.FilmService;
import com.epam.web.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateFilmCommand implements Command {
    private static final String FILMS_PAGE = "/controller?commandName=showFilmsPage&pageNumber=0";
    private final FilmService filmService;

    public UpdateFilmCommand(FilmService updateFilmService) {
        this.filmService = updateFilmService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String filmIdParam = request.getParameter("filmId");
        long id = Long.parseLong(filmIdParam);
        String genreIdParam = request.getParameter("genreId");
        long genreId = Long.parseLong(genreIdParam);
        String description = request.getParameter("description");
        String name = request.getParameter("name");
        String imagePath = request.getParameter("imagePath");
        Film updatedFilm = new Film.Builder()
                .withId(id)
                .withName(name)
                .withDescription(description)
                .withGenreId(genreId)
                .withImagePath(imagePath)
                .build();
        try {
            filmService.updateFilm(updatedFilm);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return CommandResult.redirect(request.getContextPath() + FILMS_PAGE);
    }
}
