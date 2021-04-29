package com.epam.web.command.impl;

import com.epam.web.command.Command;
import com.epam.web.command.CommandResult;
import com.epam.web.dto.FilmDTO;
import com.epam.web.entity.Genre;
import com.epam.web.service.FilmService;
import com.epam.web.service.GenreService;
import com.epam.web.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class GetFilmsCommand implements Command {

    private static final String FILM_PAGE = "/controller?commandName=mainPage&pageNumber=";
    private final FilmService filmService;
    private final GenreService genreService;

    public GetFilmsCommand(FilmService filmService, GenreService genreService) {
        this.filmService = filmService;
        this.genreService = genreService;
    }


    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String page = request.getParameter("pageNumber");
        int pageNumber = Integer.parseInt(page);

        List<FilmDTO> movies = filmService.getNextMovies(pageNumber);
        int totalPages = filmService.getPagesCount();
        List<Genre> allGenres = genreService.getAllGenres();
        HttpSession session = request.getSession();
        session.setAttribute("genres", allGenres);
        session.setAttribute("movies", movies);
        session.setAttribute("pageNumber", pageNumber);
        session.setAttribute("pagesCount", totalPages);
        return CommandResult.redirect(FILM_PAGE + pageNumber);
    }
}
