package com.epam.web.command.impl.forward;

import com.epam.web.command.Command;
import com.epam.web.command.CommandResult;
import com.epam.web.command.impl.Commands;
import com.epam.web.dto.FilmDTO;
import com.epam.web.entity.Genre;
import com.epam.web.service.FilmService;
import com.epam.web.service.GenreService;
import com.epam.web.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class FilmSearchPageCommand implements Command {
    private static final String FILM_PAGE = Commands.SEARCH_PAGE_PATH.getName();
    private static final String SEARCH_STRING = "searchString";
    private final FilmService filmService;
    private final GenreService genreService;

    public FilmSearchPageCommand(FilmService searchFilmService, GenreService genresService) {
        this.filmService = searchFilmService;
        this.genreService = genresService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String filmName = request.getParameter(SEARCH_STRING);
        List<FilmDTO> movies = filmService.getByName(filmName);
        request.setAttribute("movies", movies);
        List<Genre> allGenres = genreService.getAllGenres();
        request.setAttribute("genres", allGenres);
        return CommandResult.forward(FILM_PAGE);
    }
}
