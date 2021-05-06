package com.epam.web.command.impl.pages;

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

public class GenreSearchPageCommand implements Command {
    private static final String SEARCH_PAGE = Commands.SEARCH_PAGE_PATH.getName();
    private final FilmService filmService;
    private final GenreService genreService;



    public GenreSearchPageCommand(FilmService genreSearchFilmService, GenreService genreService) {
        this.filmService = genreSearchFilmService;
        this.genreService = genreService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String genreName = request.getParameter("genreName");
        List<FilmDTO> filmsByGenre = filmService.getByGenreName(genreName);
        request.setAttribute("movies", filmsByGenre);
        List<Genre> allGenres = genreService.getAllGenres();
        request.setAttribute("genres", allGenres);
        return CommandResult.forward(SEARCH_PAGE);
    }
}