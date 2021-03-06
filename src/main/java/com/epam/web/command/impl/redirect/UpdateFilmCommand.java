package com.epam.web.command.impl.redirect;

import com.epam.web.command.Command;
import com.epam.web.command.CommandResult;
import com.epam.web.command.impl.Commands;
import com.epam.web.entity.Film;
import com.epam.web.parser.FormParser;
import com.epam.web.parser.ParseResult;
import com.epam.web.service.FilmService;
import com.epam.web.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class UpdateFilmCommand implements Command {
    private static final String FILMS_PAGE = Commands.FILMS_PAGE + "&pageNumber=";
    private static final String PAGE_NUMBER = "pageNumber";
    private final FilmService filmService;
    private final FormParser parser;

    public UpdateFilmCommand(FilmService updateFilmService, FormParser parser) {
        this.filmService = updateFilmService;
        this.parser = parser;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        ParseResult parseResult = parser.parseFormData(request);
        Film film = parseResult.getFilm();
        List<Long> updatedGenres = parseResult.getGenresId();
        filmService.updateFilm(film, updatedGenres);
        HttpSession session = request.getSession();
        Integer pageNumber = (Integer) session.getAttribute(PAGE_NUMBER);
        return CommandResult.redirect(FILMS_PAGE + pageNumber);
    }
}
