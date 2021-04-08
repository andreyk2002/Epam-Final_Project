package com.epam.web.command.impl;

import com.epam.web.command.Command;
import com.epam.web.command.CommandResult;
import com.epam.web.dao.DaoException;
import com.epam.web.entity.Film;
import com.epam.web.service.FilmService;
import com.epam.web.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.file.Paths;
import java.util.Optional;

public class SaveFilmCommand implements Command {

    private static final String IMAGE_PATH = "static/img/movies/";
    private static final String FILMS_PAGE = "/controller?commandName=showFilmsPage&pageNumber=0";
    private final FilmService filmService;

    public SaveFilmCommand(FilmService saveFilmService) {
        this.filmService = saveFilmService;
    }


    //TODO : how should we save image to predifined path
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String name = request.getParameter("filmName");
        String genreIdParam = request.getParameter("genreId");
        Optional<String> optionalDescription = Optional.ofNullable(request.getParameter("filmDescription"));
        Optional<String> optionalImagePath = Optional.ofNullable(request.getParameter("imagePath"));
        String description = optionalDescription.orElse("");
        String imagePath = optionalImagePath.orElse("");
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
