package com.epam.web.command;

import com.epam.web.dto.FilmDTO;
import com.epam.web.service.FilmService;
import com.epam.web.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class SearchFilmCommand implements Command {
    private static final String FILM_PAGE = "/controller?commandName=searchPage";
    private static final String SEARCH_STRING = "searchString";
    private final FilmService filmService;

    public SearchFilmCommand(FilmService searchFilmService) {
        this.filmService = searchFilmService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String filmName = request.getParameter(SEARCH_STRING);
        List<FilmDTO> movies = filmService.getMoviesByName(filmName);
        HttpSession session = request.getSession();
        session.setAttribute("movies", movies);
        return CommandResult.redirect(FILM_PAGE);
    }
}
