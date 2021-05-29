package com.epam.web.command.impl.forward;

import com.epam.web.command.Command;
import com.epam.web.command.CommandResult;
import com.epam.web.command.impl.Pages;
import com.epam.web.dto.FilmDto;
import com.epam.web.entity.Genre;
import com.epam.web.entity.User;
import com.epam.web.service.FilmService;
import com.epam.web.service.GenreService;
import com.epam.web.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class FilmSearchPageCommand implements Command {
    private static final String SEARCH_STRING = "searchString";
    private final FilmService filmService;
    private final GenreService genreService;

    public FilmSearchPageCommand(FilmService searchFilmService, GenreService genresService) {
        this.filmService = searchFilmService;
        this.genreService = genresService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String filmName = request.getParameter(SEARCH_STRING);
        List<FilmDto> movies = filmService.getByName(filmName, user.getId());
        request.setAttribute("movies", movies);
        List<Genre> allGenres = genreService.getAllGenres();
        request.setAttribute("genres", allGenres);
        return CommandResult.forward(Pages.SEARCH_PAGE);
    }
}
