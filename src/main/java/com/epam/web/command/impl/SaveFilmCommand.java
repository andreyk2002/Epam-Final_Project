package com.epam.web.command.impl;

import com.epam.web.command.Command;
import com.epam.web.command.CommandResult;
import com.epam.web.entity.Film;
import com.epam.web.parser.FormParser;
import com.epam.web.service.FilmService;
import com.epam.web.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SaveFilmCommand implements Command {

    private static final String FILMS_PAGE = "/controller?commandName=showFilmsPage&pageNumber=";
    private final FilmService filmService;
    private final FormParser parser;


    public SaveFilmCommand(FilmService saveFilmService, FormParser parser) {
        this.filmService = saveFilmService;
        this.parser = parser;
    }


    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        Film film = parser.parseFormData(request);
        filmService.saveFilm(film);
        HttpSession session = request.getSession();
        Integer pageNumber = (Integer) session.getAttribute("pageNumber");
        return CommandResult.redirect(FILMS_PAGE + pageNumber);
    }

}
