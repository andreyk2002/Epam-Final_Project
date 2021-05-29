package com.epam.web.command.impl.redirect;

import com.epam.web.command.Command;
import com.epam.web.command.CommandResult;
import com.epam.web.command.impl.Commands;
import com.epam.web.entity.Film;
import com.epam.web.parser.FormParser;
import com.epam.web.parser.ParseResult;
import com.epam.web.service.FilmGenreService;
import com.epam.web.service.FilmService;
import com.epam.web.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class SaveFilmCommand implements Command {

    private static final String FILMS_PAGE = Commands.FILMS_PAGE + "&pageNumber=";
    private final FilmService filmService;
    private final FormParser parser;


    public SaveFilmCommand(FilmService saveFilmService, FormParser parser) {
        this.filmService = saveFilmService;
        this.parser = parser;
    }


    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        ParseResult parseResult = parser.parseFormData(request);
        Film film = parseResult.getFilm();
        List<Long> genresId = parseResult.getGenresId();
        filmService.saveFilm(film, genresId);

        HttpSession session = request.getSession();
        Integer pageNumber = (Integer) session.getAttribute("pageNumber");
        return CommandResult.redirect(FILMS_PAGE + pageNumber);
    }

}
